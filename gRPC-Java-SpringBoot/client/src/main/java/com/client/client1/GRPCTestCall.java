package com.client.client1;


import com.github.antezovko23.ClientRequest;





public class GRPCTestCall {

    

    public void GRPCCall() throws InterruptedException {
        ClientRequest incrementGrpcRequest = new ClientRequest();
        int counter = 0;
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