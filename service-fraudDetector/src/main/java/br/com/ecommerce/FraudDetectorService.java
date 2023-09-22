package br.com.ecommerce;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {


	private final KafkaDispatcher<Order> kafkaDispatcher = new KafkaDispatcher<Order>();
	public static void main(String[] args) {
		var fraudDetectorService = new FraudDetectorService();
		var kafkaService = new KafkaService<Order>(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER", fraudDetectorService::parse, Order.class, new HashMap<>());
		
		kafkaService.run();
		
	}
		
		
	private void parse (ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
					System.out.println("---------------------");
					System.out.println("processing new order");
					System.out.println(record.key());
					System.out.println(record.value());
					System.out.println(record.partition());
					System.out.println(record.offset());

					var order = record.value();
					if (isFraud(order)){
						kafkaDispatcher.send("ECOMMERCE_FRAUD_DETECTED",order.getUserId(),order);
					} else {
						kafkaDispatcher.send("ECOMMERCE_ORDER_APPROVED",order.getUserId(),order);
					}

	}

	private boolean isFraud(Order order) {
		return order.getValue().compareTo(new BigDecimal("4000")) >= 0;
	}

}
