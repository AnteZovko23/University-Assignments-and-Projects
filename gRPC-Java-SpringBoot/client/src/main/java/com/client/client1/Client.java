package com.client.client1;

import com.github.antezovko23.GRPCContinuousIncrementRequest;
import com.github.antezovko23.ServerConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Client {

	public static void main(String[] args) throws InterruptedException {
		// SpringApplication.run(Client.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Client.class, args);

		ServerConnection serverConnection = context.getBean(ServerConnection.class);
		GRPCContinuousIncrementRequest incrementCall = context.getBean(GRPCContinuousIncrementRequest.class);

		// serverConnection.incrementRequest(3, 10);

		try {
			incrementCall.GRPCCall(2, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
