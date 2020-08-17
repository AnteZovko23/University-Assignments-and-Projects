package com.github.antezovko23;

import org.springframework.stereotype.Service;

/**
 * Creates continous incrementation based on 
 * a given number and step
 * 
 */
@Service
public class GRPCContinuousIncrementRequest {

     /**
     * Send number to be incremented
     * 
     * @param specifier [optional] "start" or "step"
     * @param start the start value [default=0]
     * @param step [default=1] the increment step
     * @throws InterruptedException
     * 
     */
    public void GRPCCall(int startNum, int incrementStep) throws InterruptedException {
        ServerConnection incrementGrpcRequest = new ServerConnection();


        int dataToSend = incrementGrpcRequest.sendIncrementRequest(startNum, incrementStep);

        while(true){
            System.out.println(dataToSend);
            dataToSend = incrementGrpcRequest.sendIncrementRequest(dataToSend, incrementStep);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        }
    }


    /****************** Overloading for default params *********************/

    /**
     * Send number to be incremented
     * 
     * @param specifier [optional] "start" or "step"
     * @param start the start value [default=0]
     * @param step [default=1] the increment step
     * @throws InterruptedException
     * 
     */
    public void GRPCCall() throws InterruptedException {
        GRPCCall(0, 1);
    }
   
     /**
      * Send number to be incremented
      * 
      * @param specifier [optional] "start" or "step"
      * @param start     the start value [default=0]
      * @param step      [default=1] the increment step
      * @throws Exception
      * 
      */
     public void GRPCCall(String specifier, int num) throws Exception {
        if(specifier.equals("start")){
            GRPCCall(num, 1);
        }
        else if(specifier.equals("step")){
            GRPCCall(0, num);
        }
        else {
            throw new Exception("Please enter a proper specifier");
        }
    }

     /****************** Overloading for default params *********************/

    
}