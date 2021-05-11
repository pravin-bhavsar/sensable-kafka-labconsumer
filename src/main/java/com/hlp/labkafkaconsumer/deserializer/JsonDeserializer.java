package com.hlp.labkafkaconsumer.deserializer;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Firdose
 *
 */
public class JsonDeserializer implements org.apache.kafka.common.serialization.Deserializer {

	public void configure(Map configs, boolean isKey) {
		// Auto-generated method stub

	}

	public Object deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			object = mapper.readValue(data, Object.class);
		} catch (Exception exception) {
		}
		return object;
	}

	public void close() {
		// Auto-generated method stub

	}
}
