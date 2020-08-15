package com.client.client1;





import com.github.antezovko23.ClientRequest;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
public class Controller {


    @Autowired
    private ClientRequest incrementGrpcRequest;

    @RequestMapping("/increment")
    public int incrementRequest(@RequestParam(defaultValue = "2") Integer counter){
        
        while(true){
            System.out.println(incrementGrpcRequest.sendIncrementRequest(counter));
            counter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        }
    }

    
}