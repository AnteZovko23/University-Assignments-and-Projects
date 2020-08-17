package com.client.client1.config;


import com.github.antezovko23.GRPCContinuousIncrementRequest;
import com.github.antezovko23.ServerConnection;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration Class 
 * 
 * 
 */
@Configuration
public class ConfigurationClass {

    @Bean
    public ServerConnection getRequest(){
        return new ServerConnection();
    }

    @Bean
    public GRPCContinuousIncrementRequest GRPCContinuousIncrementRequest() {
        return new GRPCContinuousIncrementRequest();
    }


    
}