package com.client.client1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);

		GRPCTestCall impl = new GRPCTestCall();

		impl.GRPCCall();
		
		
	}

}
