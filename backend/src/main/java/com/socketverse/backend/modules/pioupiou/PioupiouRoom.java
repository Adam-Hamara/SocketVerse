package com.socketverse.backend.modules.pioupiou;

import com.socketverse.backend.modules.IGameRoom;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PioupiouRoom implements IGameRoom {

    public static final int MAX_PLAYERS = 5;
    public static final int MIN_PLAYERS = 2;

    public static final int FOX_CARDS = 6;
    public static final int ROOSTER_CARDS = 15;
    public static final int CHICKEN_CARDS = 15;
    public static final int HATCH_CARDS = 11;

    public static final String FOX = "FOX";
    public static final String ROOSTER = "ROOSTER";
    public static final String CHICKEN = "CHICKEN";
    public static final String HATCH = "HATCH";

    public static final int EGGS = 18;
    public static final String EGG = "EGG";
    public static final String HATCHED_EGG = "HATCHED_EGG";

    public List<String> deck = new ArrayList<>(); // full deck of cards
    public List<String> pile = new ArrayList<>(); // cards in a pile to be reused when deck runs out of cards
    public List<PioupiouPlayer> players = new ArrayList<>();
    public int numberOfCurrentPLayers = 0;
    public int hatchedEggs = 0;

    public PioupiouRoom(){

        for(int i = 0; i < FOX_CARDS; i++){
            deck.add(FOX);
        }
        for(int i = 0; i < ROOSTER_CARDS; i++){
            deck.add(ROOSTER);
        }
        for(int i = 0; i < CHICKEN_CARDS; i++){
            deck.add(CHICKEN);
        }
        for(int i = 0; i < HATCH_CARDS; i++){
            deck.add(HATCH);
        }
        Collections.shuffle(deck);
    }

    @Override
    public void addPlayer(String name, WebSocketSession session){
        players.add(new PioupiouPlayer(name, session));
    }

    public void action(){

    }


    @Override
    public String getName(){
        return "PioupiouRoom";
    }



}

