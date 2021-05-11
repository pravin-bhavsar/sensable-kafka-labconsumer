package com.hlp.labkafkaconsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hlp.labkafkaconsumer.constants.IKafkaConstants;
import com.hlp.labkafkaconsumer.consumer.ConsumerCreator;
import com.hlp.labkafkaconsumer.pojo.Lab;

/**
 * 
 * @author Firdose
 *
 */
@SpringBootApplication
public class LabKafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(LabKafkaConsumer.class);

	public static void main(String[] args) {
		SpringApplication.run(LabKafkaConsumer.class, args);
		runConsumer();
	}

	static void runConsumer() {
		logger.debug("Running Consumer for receiving message");

		ConsumerCreator consumerCreator = new ConsumerCreator();
		final Consumer<Long, Lab> consumer = consumerCreator.createConsumer();
		try {
			consumer.listTopics();

			int noMessageToFetch = 0;

			while (true) {
				final ConsumerRecords<Long, Lab> consumerRecords = consumer.poll(1000);
				if (consumerRecords.count() == 0) {
					noMessageToFetch++;
					if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
						break;
					else
						continue;
				}
				for (ConsumerRecord<Long, Lab> consumerRecord : consumerRecords) {
					logger.info("received message key is {} value is {} partition is {} and offset is {}",
							consumerRecord.key(), consumerRecord.value(), consumerRecord.partition(),
							consumerRecord.offset());
				}
				consumer.commitAsync();
			}
			consumer.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			consumer.close();
		}
	}
}
