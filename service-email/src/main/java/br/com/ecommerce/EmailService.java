package br.com.ecommerce;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
	
	
	public static void main(String[] args) {
		
		var emailService = new EmailService();
		var service = new KafkaService<String>(EmailService.class.getSimpleName(),"ECOMMERCE_SEND_EMAIL", emailService::parse, String.class, Map.of());
		
		service.run();
		
	}
	
	public void parse (ConsumerRecord<String, String> record) {
		System.out.println("---------------------");
		System.out.println("sending email");
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.partition());
		System.out.println(record.offset());
	}
}
