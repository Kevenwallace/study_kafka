package ecomerce.kafka;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
	
	public static void main(String[] args) {
		var fraudDetectorService = new FraudDetectorService();
		var kafkaService = new KafkaService<Order>(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER", fraudDetectorService::parse, Order.class, new HashMap<>());
		
		kafkaService.run();
		
	}
		
		
	private void parse (ConsumerRecord<String, Order> record) {
					System.out.println("---------------------");
					System.out.println("processing new order");
					System.out.println(record.key());
					System.out.println(record.value());
					System.out.println(record.partition());
					System.out.println(record.offset());
		
	}


}
