package com.grpcimplementation.restapi.model;


public class ResponseModel {

    private int id;
    private String content;

    public ResponseModel(int id, String content) {
        this.setId(id);
        this.setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}