package com.hlp.labkafkaconsumer.constants;

/**
 * 
 * @author Firdose
 *
 */
public class IKafkaConstants {
	public static final String KAFKA_BROKERS = "ec2-18-188-231-20.us-east-2.compute.amazonaws.com:9092";

	public static final Integer MESSAGE_COUNT = 1000;

	public static final String CLIENT_ID = "client1";

	public static final String TOPIC_NAME = "LAB";

	public static final String GROUP_ID_CONFIG = "consumerGroup10";

	public static final Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;

	public static final String OFFSET_RESET_LATEST = "latest";

	public static final String OFFSET_RESET_EARLIER = "earliest";

	public static final Integer MAX_POLL_RECORDS = 1;
}
