package ecomerce.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class NewOrderMain {

	
	public static void main(String[] args) {
		var producer = new KafkaProducer<String, String>(properties());
		var value = "1222333,keven,12234444";
		ProducerRecord<String, String> record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
        producer.send(record);

		    }
		    private static Properties properties() {
		                 var properties = new Properties();
		                 properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		                 properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		                 properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		                 return properties;
		          
	}

}
