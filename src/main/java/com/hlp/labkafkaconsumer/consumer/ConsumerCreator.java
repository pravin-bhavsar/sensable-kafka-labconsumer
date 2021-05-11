package com.hlp.labkafkaconsumer.consumer;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlp.labkafkaconsumer.constants.IKafkaConstants;
import com.hlp.labkafkaconsumer.deserializer.JsonDeserializer;
import com.hlp.labkafkaconsumer.pojo.Lab;

/**
 * 
 * @author Firdose
 *
 */
public class ConsumerCreator {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerCreator.class);

	Consumer<Long, Lab> consumer;

	public Consumer<Long, Lab> createConsumer() {
		logger.debug("creating Consumer");
		if (this.consumer != null) {
			return this.consumer;
		} else {
			final Properties props = new Properties();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
			props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
			props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);

			this.consumer = new KafkaConsumer<Long, Lab>(props);
			consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));
			return this.consumer;
		}
	}
}
