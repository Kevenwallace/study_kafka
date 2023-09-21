package ecomerce.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaService<T> {
	private final KafkaConsumer<String, T> consumer;
	private final ConsumerFunction<T> parse;
	private Class<T> type;
	
	KafkaService(String simpleName, String topic, ConsumerFunction<T> parse, Class<T> type) {
		this.type = type;
		this.parse = parse;
		this.consumer = new KafkaConsumer<>(properties(simpleName));
		consumer.subscribe(Collections.singletonList(topic));

	}
	
	KafkaService(String simpleName, Pattern topic, ConsumerFunction<T> parse, Class<T> type) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<>(properties(simpleName));
		consumer.subscribe(topic);

	}

	


	public void run () {
		while (true) {
			var records = consumer.poll(Duration.ofMillis(100));
			if (!records.isEmpty()) {
				System.out.println("encontrei"+records.count()+ "registros");
				for (var record: records) {
					parse.consumer(record);
				}
			}
		}
	}
	
	private Properties properties(String simpleName) {
		var properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, simpleName);
		properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
		return properties;
	}


}
