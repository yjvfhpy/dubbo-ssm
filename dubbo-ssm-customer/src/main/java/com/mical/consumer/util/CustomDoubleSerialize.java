/**
 * 
 */
package com.mical.consumer.util;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @Description:
 * @author micalliu
 * @date 2017年6月22日
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

	private DecimalFormat df = new DecimalFormat("##.00");

	/* 
	 * 
	 */
	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		gen.writeString(df.format(value));
	}

}
