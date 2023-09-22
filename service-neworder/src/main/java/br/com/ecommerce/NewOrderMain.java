package br.com.ecommerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try (var kafkaDispatcher = new KafkaDispatcher<Order>()) {
			try (var kafkaDispatcher1 = new KafkaDispatcher<String>()) {
				for (int i = 0; i < 10; i ++) {
				var key = UUID.randomUUID().toString();
				
				var value = "1222333,keven wallace,12234444";
				var amout = new BigDecimal(Math.random() * 5000 + 1);
				var order = new Order(key, value, amout);
				kafkaDispatcher.send("ECOMMERCE_NEW_ORDER", key, order);		
				var email = "congratulations for you buy";
				kafkaDispatcher1.send("ECOMMERCE_SEND_EMAIL", key,email);}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    }


}
