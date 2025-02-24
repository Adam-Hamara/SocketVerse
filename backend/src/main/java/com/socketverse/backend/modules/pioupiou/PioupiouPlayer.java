package com.socketverse.backend.modules.pioupiou;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class PioupiouPlayer {
    public String id;
    public String name;
    public List<String> cardsOnHand;
    public List<String> eggsInPossession;
    public WebSocketSession session;

    public PioupiouPlayer(String name, WebSocketSession session){
        this.id = session.getId();
        this.name = name;
        this.cardsOnHand = new ArrayList<>();
        this.eggsInPossession = new ArrayList<>();
        this.session = session;
    }

}
