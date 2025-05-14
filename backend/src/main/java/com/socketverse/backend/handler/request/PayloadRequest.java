package com.socketverse.backend.handler.request;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PayloadRequest {

    public PayloadType payloadType = PayloadType.UNKNOWN;

    public Request payloadRequest;

    public PayloadRequest(String payloadString){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payloadRequest = objectMapper.reader().readValue(payloadString, Request.class);
            if(payloadRequest != null && payloadRequest.payload != null){
                if(payloadRequest.payload.action != null){
                    payloadType = PayloadType.DATA;
                }
                else if(payloadRequest.payload.message != null){
                    payloadType = PayloadType.STRING;
                }
                else{
                    payloadType = PayloadType.OTHER;
                }
            }
        } catch (IOException ignored) {
            payloadType = PayloadType.ERROR;
        }

    }



}
