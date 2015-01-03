package org.openxava.annotations.parse;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldDefaultValueResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(FieldDefaultValueResolver.class);

	public static boolean isNotDefault(Object value, String fieldName, Class<?> clazz) throws Exception {
		try {
			if(value != null) {
				Field field = clazz.getDeclaredField(fieldName);

				DefaultValue defaultValueAnn = field.getAnnotation(DefaultValue.class);
				if(defaultValueAnn != null) {
					return isNotDefault(value, defaultValueAnn.value());
				} else {
					return isNotJavaDefault(value);
				}
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isNotDefault(Object fieldValue, String defaultValue) throws Exception {
		Class<?> fieldType = fieldValue.getClass();
		if(fieldType.equals(Integer.class)) {
			int defaultValue_ = Integer.parseInt(defaultValue);
			int value_ = Integer.valueOf(String.valueOf(fieldValue)).intValue();
			if(value_ != defaultValue_)
				return true;
		} else if(fieldType.equals(Boolean.class)) {
			boolean defaultValue_ = Boolean.parseBoolean(defaultValue);
			boolean value_ = Boolean.valueOf(String.valueOf(fieldValue)).booleanValue();
			if(value_ != defaultValue_)
				return true;
		} else if(fieldType.equals(Long.class)) {
			long defaultValue_ = Long.parseLong(defaultValue);
			long value_ = Long.valueOf(String.valueOf(fieldValue)).longValue();
			if(value_ != defaultValue_)
				return true;
		} else if(fieldType.equals(Double.class)) {
			double defaultValue_ = Double.parseDouble(defaultValue);
			double value_ = Double.valueOf(String.valueOf(fieldValue)).doubleValue();
			if(value_ != defaultValue_)
				return true;
		} else if(fieldType.equals(Float.class)) {
			float defaultValue_ = Float.parseFloat(defaultValue);
			float value_ = Float.valueOf(String.valueOf(fieldValue)).floatValue();
			if(value_ != defaultValue_)
				return true;
		} else if(fieldType.equals(String.class)) {
			String value_ = String.valueOf(fieldValue);
			if(!value_.equals(defaultValue))
				return true;
		} else if(fieldType.equals(Calendar.class)) {
			
		} else if(fieldType.equals(Date.class)) {
			
		} else if(fieldType.isEnum()) {
			Enum enumConstant = (Enum) fieldValue;
			try {
				Method method = enumConstant.getDeclaringClass().getMethod("getValue");
				Object enumValue = method.invoke(enumConstant);
				return isNotDefault(enumValue, defaultValue);
			} catch (Exception e) {
				logger.error("The enum " + enumConstant.getDeclaringClass() + " should declare a method with name getValue()");
				throw e;
			}
		}
		return false;
	}

	public static boolean isNotJavaDefault(Object fieldValue) {
		Class<?> fieldType = fieldValue.getClass();
		if(fieldType.equals(Integer.class)) {
			return Integer.valueOf(String.valueOf(fieldValue)).intValue() != 0;
		} else if(fieldType.equals(Boolean.class)) {
			return Boolean.valueOf(String.valueOf(fieldValue)).booleanValue() != false;
		} else if(fieldType.equals(Long.class)) {
			return Long.valueOf(String.valueOf(fieldValue)).longValue() != 0L;
		} else if(fieldType.equals(Double.class)) {
			return Double.valueOf(String.valueOf(fieldValue)).doubleValue() != 0.0d;
		} else if(fieldType.equals(Float.class)) {
			return Float.valueOf(String.valueOf(fieldValue)).floatValue() != 0.0f;
		} else if(fieldType.equals(String.class)) {
			return !String.valueOf(fieldValue).isEmpty();
		} else if(fieldType.equals(Calendar.class)) {
			
		} else if(fieldType.equals(Date.class)) {
			
		} else if(fieldType.isEnum()) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void handleEnumType(Class<?> type, Object fieldValue) {
		try {
			Object[] enumConstants = type.getEnumConstants();
			for (Object constant : enumConstants) {
				Enum enumConstant = (Enum) constant;
				Method method = enumConstant.getDeclaringClass().getMethod("getValue");
				Object enumValue = method.invoke(enumConstant);
				if(enumValue.equals(fieldValue)) {
					// TODO
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isSimpleType(Class<?> fieldType) {
		return fieldType.equals(String.class)
				|| fieldType.equals(Integer.class)
				|| fieldType.equals(Boolean.class)
				|| fieldType.equals(Long.class)
				|| fieldType.equals(Double.class)
				|| fieldType.equals(Float.class)
				|| fieldType.equals(Calendar.class)
				|| fieldType.equals(Date.class);
	}
	
	public static boolean isReferenceType(Class<?> fieldType) {
		return !isSimpleType(fieldType);
	}
	
}
