package com.grpcimplementation.restapi;

import java.util.concurrent.atomic.AtomicInteger;

import com.grpcimplementation.restapi.model.ResponseModel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Response {

    private static final String template = "Hello, %s";
    private final AtomicInteger counter = new AtomicInteger();

    @RequestMapping("/response")
    public @ResponseBody ResponseModel response(
        @RequestParam(value = "name", required = false, defaultValue = "World") String name){
            return new ResponseModel(counter.incrementAndGet(), String.format(template, name));
        }
}