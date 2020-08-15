package com.client.client1.config;

import com.client.client1.GRPCTestCall;
import com.github.antezovko23.ClientRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public ClientRequest getRequest(){
        return new ClientRequest();
    }

    @Bean
    public GRPCTestCall getGRPCTestCall(){
        return new GRPCTestCall();
    }
    
    
}