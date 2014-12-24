package org.plugmin.core.config.context.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Adds support for Jackson's JsonView on methods annotated with a
 * {@link ResponseView} annotation
 * 
 * @author Varun Kumar
 *
 */
public class ViewAwareJsonMessageConverter extends
		MappingJackson2HttpMessageConverter {

	public ViewAwareJsonMessageConverter() {
		super();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		mapper.disable(SerializationFeature.INDENT_OUTPUT); // in dev mode, it
															// can be enabled
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		mapper.setDateFormat(dateFormat);
		
		setObjectMapper(mapper);
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		writeNormal(object, outputMessage);
	}

	protected void writeNormal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
				.getContentType());

		ObjectMapper mapper = getObjectMapper();

		ObjectWriter objectWriter = mapper.writer();

		JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(
				outputMessage.getBody(), encoding);

		try {
			objectWriter.writeValue(jsonGenerator, object);
		} catch (IOException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

}
