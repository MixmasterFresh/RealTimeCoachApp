package com.example.austin.realtimecoach;

import java.util.ArrayList;

/**
 * Created by Austin on 2/15/2015.
 */
public class PlayerList {
    ArrayList<Player> players=new ArrayList<>();
    public PlayerList()
    {

    }
    public void addPlayer(Player player)
    {
        players.add(player);
    }

}
