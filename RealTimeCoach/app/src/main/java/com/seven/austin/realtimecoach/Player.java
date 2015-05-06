package com.seven.austin.realtimecoach;

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
    int hseverity=0;
    double x;
    double y;
    double z;
    double hx;
    double hy;
    double hz;
    double intercept = 1;//TODO:determine intercept
    boolean valid = true;

    public Player(String first_name, String last_name, int number, int xbee)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.number=number;
        this.xbee=xbee;
    }

    public void setHeartRate(int heartRate)
    {
        if(heartRate <0) {

        }
        else {
            this.heartRate=heartRate;
        }

    }

    public void setCollisionSeverity(short a, short b, short c)
    {
        x = ((double)a) * conversion + intercept;
        y = ((double)b) * conversion + intercept;
        z = ((double)c) * conversion + intercept;
        force = 1;
        force=Math.sqrt(Math.pow(x, 2)+Math.pow(y,2)+Math.pow(z,2));
        if(force<100 && severity<2)//TODO:determine limits
        {
            severity=1;
        }
        else if(force<1000 && severity<3)
        {
            severity=2;
        }
        else
        {
            severity=3;
        }
    }

    public void setHeadCollisionSeverity(short a, short b, short c)
    {
        hx = ((double)a) * conversion + intercept;
        hy = ((double)b) * conversion + intercept;
        hz = ((double)c) * conversion + intercept;
        force = 1;
        force=Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
        if(force<100 && hseverity<2)//TODO:determine limits
        {
            hseverity=1;
        }
        else if(force<1000 && hseverity<3)
        {
            hseverity=2;
        }
        else
        {
            hseverity=3;
        }
    }

    @Override
    public int compareTo(Object player1) {
        Integer a=new Integer(number);
        Integer b=new Integer(((Player)player1).number);
        return a.compareTo(b);
    }
}
