package com.client.client1;

import com.github.antezovko23.GRPCContinuousIncrementRequest;
import com.github.antezovko23.ServerConnection;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {


    // Dependency injections 
    @Autowired
    private ServerConnection serverConnection;
    @Autowired
    private GRPCContinuousIncrementRequest incrementCall;


    // Increments a number by a given value
    @RequestMapping("/increment")
    public String incrementRequest(@RequestParam(defaultValue = "0") Integer counter,
            @RequestParam(defaultValue = "1") Integer step) {

       
            System.out.println(serverConnection.sendIncrementRequest(counter, step));
            return "Done";

        }
    

    // Constantly increments number by a given value
    @RequestMapping("/continuousIncrement")
    public void continuousIncrementRequest(@RequestParam(defaultValue = "0") Integer counter,
            @RequestParam(defaultValue = "1") Integer step) {

        try {
            incrementCall.GRPCCall(counter, step);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        

        }
    }

    
