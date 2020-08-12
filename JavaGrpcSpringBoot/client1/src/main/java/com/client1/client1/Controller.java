package com.client1.client1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Client1Request grpcClientService;

    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "Ante") String name) {
        return grpcClientService.receiveGreeting(name);
    }

}