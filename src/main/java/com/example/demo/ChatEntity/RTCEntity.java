package com.example.demo.ChatEntity;

import java.time.LocalDateTime;


public class RTCEntity {
	
    private String type;
    private String sdp;
    private CallEntity call;

    // Construtor padrão
    public RTCEntity() {
    }

    // Construtor com parâmetros
    public RTCEntity(String type, String sdp, CallEntity call) {
        this.type = type;
        this.sdp = sdp;
        this.call = call;
    }

    // Getters e Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }
    

    public CallEntity getCall() {
        return call;
    }

    public void setCall(CallEntity call) {
        this.call = call;
    }
	
}