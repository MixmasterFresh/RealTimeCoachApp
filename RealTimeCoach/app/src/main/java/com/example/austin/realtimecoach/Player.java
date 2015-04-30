package com.example.austin.realtimecoach;

/**
 * Created by Austin on 2/14/2015.
 */
public class Player implements Comparable {
    String first_name;
    String last_name;
    int number;
    final double conversion = 1;//TODO:determine conversion
    int xbee;
    int heartRate;
    double force;
    int severity=0;
    double x;
    double y;
    double z;

    public Player(String first_name, String last_name, int number, int xbee)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.number=number;
        this.xbee=xbee;
    }
    public void setHeartRate(int heartRate)
    {
        this.heartRate=heartRate;
    }
    public void setCollisionSeverity(short a, short b, short c)
    {
        x = a * conversion;
        y = b * conversion;
        z = c * conversion;
        force = 1;
        //force=Math.sqrt((x^2)+(y^2)+(z^2));
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

    @Override
    public int compareTo(Object player1) {
        Integer a=new Integer(number);
        Integer b=new Integer(((Player)player1).number);
        return a.compareTo(b);
    }
}
