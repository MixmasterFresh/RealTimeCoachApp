package com.example.austin.realtimecoach;

/**
 * Created by Austin on 2/14/2015.
 */
public class Player {
    String name;
    int number;
    int xbee;
    int heartRate;
    double force;
    int severity=0;
    public Player(String name, int number, int xbee)
    {
        this.name=name;
        this.number=number;
        this.xbee=xbee;
    }
    public void setHeartRate(int heartRate)
    {
        this.heartRate=heartRate;
    }
    public void setCollisionSeverity(double force)
    {
        this.force=force;
        if(force<100)
        {
            severity=1;
        }
        else if(force<1000)
        {
            severity=2;
        }
        else
        {
            severity=3;
        }
    }
}
