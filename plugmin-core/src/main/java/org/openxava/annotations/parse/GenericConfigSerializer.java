package org.openxava.annotations.parse;

import static org.openxava.annotations.parse.FieldDefaultValueResolver.isNotDefault;
import static org.openxava.annotations.parse.FieldDefaultValueResolver.isSimpleType;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * All classes calling this method should not have any primitive fields. Arrays can
 * be primitive. The reason for not allowing primitive fields is, if a field has
 * a annotation @DefaultValue("true"), its value is written as false even if the
 * user has not given any value, because of the default value of primitive
 * boolean. This won't happen in the case of reference Boolean.
 * 
 * Similar case for a field with annotation @DefaultValue("0"), because of the
 * default value of primitive integer.
 * 
 * Due to the above reasons, all the config classes should not have any
 * primitives except arrays.
 * 
 * @author Varun Kumar
 *
 */
public class GenericConfigSerializer extends JsonSerializer<Object> {

	
	@SuppressWarnings("unchecked")
	@Override
	public void serialize(Object config, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		List<String> compositeFieldsVisited = new ArrayList<String>();
		
		gen.writeStartObject();

		Class<? extends Object> clazz = config.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		
		for (Field field : declaredFields) {
			try {
				field.setAccessible(true);
				String fieldName = field.getName();
				Class<?> fieldType = field.getType();
				Object fieldValue = field.get(config);
				
				JsonRawKeyValueProvider jsonRawKeyValueProvider = field.getAnnotation(JsonRawKeyValueProvider.class);
				if(jsonRawKeyValueProvider != null) {
					Method method = clazz.getMethod("getRawKeyValues", String.class);
					fieldValue = method.invoke(config, fieldName);
					Map<String, String> map = (Map<String, String>) fieldValue;
					if(map != null) {
						writeRawKeyValues(gen, config, fieldName, map);
					}
					continue;
				}
				
				JsonKeyValueProvider jsonKeyValueProvider = field.getAnnotation(JsonKeyValueProvider.class);
				if(jsonKeyValueProvider != null) {
					Method method = clazz.getMethod("getKeyValues", String.class);
					fieldValue = method.invoke(config, fieldName);
					Map<String, Object> map = (Map<String, Object>) fieldValue;
					if(map != null) {
						writeKeyValues(gen, config, fieldName, map);
					}
					continue;
				}
				
				JsonValueProvider jsonValueProvider = field.getAnnotation(JsonValueProvider.class);
				if(jsonValueProvider != null) {
					Method method = clazz.getMethod("getValue", String.class);
					fieldValue = method.invoke(config, fieldName);
					if(fieldValue != null) {
						writeJson(gen, config, fieldName, fieldValue);
					}
					continue;
				}

				CompositeField compositeField = field.getAnnotation(CompositeField.class);
				if(compositeField != null) {
					String compositeId = compositeField.value();
					if(!compositeFieldsVisited.contains(compositeId)) {
						compositeFieldsVisited.add(compositeId);
						processCompositeField(gen, config, compositeId);
					}
					continue;
				}

				JsonIgnore jsonIgnore = field.getAnnotation(JsonIgnore.class);
				
				if (fieldValue != null && jsonIgnore == null) {
					JsObject jsObject = field.getAnnotation(JsObject.class);
					if(jsObject != null) {
						gen.writeFieldName(fieldName);
						gen.writeRawValue(fieldValue.toString());
					} else if (isSimpleType(fieldType)) {
						if (isNotDefault(fieldValue, fieldName, clazz)) {
							writeField(gen, fieldName, fieldType, fieldValue);
						}
					} else if (Collection.class.isAssignableFrom(fieldType)) {
						if(CollectionUtils.isNotEmpty((Collection) fieldValue))
							gen.writeObjectField(fieldName, fieldValue);
					} else if (fieldType.isArray()) {
						if(Array.getLength(fieldValue) != 0)
							gen.writeObjectField(fieldName, fieldValue);
					} else if (fieldType.isEnum()) {
						if (isNotDefault(fieldValue, fieldName, clazz)) {
							gen.writeObjectField(fieldName, fieldValue);
						}
					} else {
//						gen.writeObjectField(fieldName, fieldValue);
						writeJson(gen, config, fieldName, fieldValue);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		gen.writeEndObject();
	}

	private void writeRawKeyValues(JsonGenerator gen, Object config,
			String fieldName, Map<String, String> map) throws JsonGenerationException, IOException {
		for(String key : map.keySet()) {
			gen.writeFieldName(key);
			gen.writeRawValue(map.get(key));
		}
	}

	private void writeKeyValues(JsonGenerator gen, Object config,
			String fieldName, Map<String, Object> map) throws JsonGenerationException, IOException {
		for(String key : map.keySet()) {
			gen.writeFieldName(key);
			gen.writeObject(map.get(key));
		}
	}

	private void writeJson(JsonGenerator gen, Object config, String fieldName, Object fieldValue) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fieldValue);
			if(StringUtils.isNotEmpty(json) && !json.equals("{ }") && !json.equals("[ ]") && !json.equals("{ },") && !json.equals("[ ],")) {
				gen.writeObjectField(fieldName, fieldValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processCompositeField(JsonGenerator gen, Object config, String compositeId) throws Exception {
		Class<? extends Object> clazz = config.getClass();
		
		Method resolve = config.getClass().getMethod("resolve", String.class);
		JsField jsField = (JsField) resolve.invoke(config, compositeId);

		if(jsField != null) {
			Object jsFieldValue = jsField.getFieldValue();
			String jsFieldName = jsField.getFieldName();//jsFieldName & compositeId may or may not be equal
			String compositeFieldName = jsField.getCompositeFieldName(); 
			if(jsFieldValue != null) {
				if(jsField instanceof JsFieldString) {
					if(isNotDefault(jsFieldValue, compositeFieldName, clazz))
						gen.writeStringField(jsFieldName, jsFieldValue.toString());
				} else if(jsField instanceof JsFieldVariable) {
					if(!jsFieldValue.toString().isEmpty()) {
						gen.writeFieldName(jsFieldName);
						gen.writeRawValue(jsFieldValue.toString());
					}
				} else if(jsField instanceof JsFieldBoolean) {
					if(isNotDefault(jsFieldValue, compositeFieldName, clazz))
						gen.writeBooleanField(jsFieldName, Boolean.valueOf(jsFieldValue.toString()));
				} else {
//					gen.writeObjectField(compositeId, jsFieldValue);
					writeJson(gen, config, compositeId, jsFieldValue);
				}
			}
			
		}
	}

	private void writeField(JsonGenerator gen, String fieldName,
			Class<?> fieldType, Object fieldValue)
			throws JsonGenerationException, IOException {
		if (fieldType.equals(Integer.class)) {
			int intValue = Integer.valueOf(String.valueOf(fieldValue))
					.intValue();
			gen.writeNumberField(fieldName, intValue);
		} else if (fieldType.equals(Boolean.class)) {
			boolean booleanValue = Boolean.valueOf(String.valueOf(fieldValue))
					.booleanValue();
			gen.writeBooleanField(fieldName, booleanValue);
		} else if (fieldType.equals(Long.class)) {
			long longValue = Long.valueOf(String.valueOf(fieldValue))
					.longValue();
			gen.writeNumberField(fieldName, longValue);
		} else if (fieldType.equals(Double.class)) {
			double doubleValue = Double.valueOf(String.valueOf(fieldValue))
					.doubleValue();
			gen.writeNumberField(fieldName, doubleValue);
		} else if (fieldType.equals(Float.class)) {
			float floatValue = Float.valueOf(String.valueOf(fieldValue))
					.floatValue();
			gen.writeNumberField(fieldName, floatValue);
		} else if (fieldType.equals(String.class)) {
			String stringValue = String.valueOf(fieldValue);
			gen.writeStringField(fieldName, stringValue);
		} else if (fieldType.equals(Calendar.class)) {
			gen.writeStringField(fieldName, fieldValue.toString());
		} else if (fieldType.equals(Date.class)) {
			gen.writeStringField(fieldName, fieldValue.toString());
		}

	}

}
