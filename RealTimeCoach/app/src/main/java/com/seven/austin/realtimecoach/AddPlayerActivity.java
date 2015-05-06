package com.seven.austin.realtimecoach;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.widget.EditText;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by Austin on 2/11/2015.
 */
public class AddPlayerActivity extends Activity
{

    /* Get Default Adapter */
    private String first_name="";
    private String last_name="";
    private int number=-1;
    private int xbee=-1;
    private short[] check =new short[11];
    //private EditText first_name_entry;
    //private EditText last_name_entry;
    //private EditText numberEntry;
    //private EditText xbeeEntry;
    boolean isValid = false;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
        final EditText first_name_entry=(EditText)findViewById(R.id.first);
        final EditText last_name_entry=(EditText)findViewById(R.id.last);
        final EditText numberEntry=(EditText)findViewById(R.id.number);
        final EditText xbeeEntry=(EditText)findViewById(R.id.address);
        final Button button = (Button) findViewById(R.id.final_add_player);
        first_name_entry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    first_name_entry.setHint("");
                }
                else {
                    first_name_entry.setHint("First Name");
                }
            }
        });
        last_name_entry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    last_name_entry.setHint("");
                }
                else {
                    last_name_entry.setHint("Last Name");
                }
            }
        });
        numberEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    numberEntry.setHint("");
                }
                else {
                    numberEntry.setHint("Number");
                }
            }
        });
        xbeeEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    xbeeEntry.setHint("");
                }
                else {
                    xbeeEntry.setHint("Address");
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                first_name=first_name_entry.getText().toString();
                last_name=last_name_entry.getText().toString();
                number=Integer.parseInt(numberEntry.getText().toString());
                xbee=Integer.parseInt(xbeeEntry.getText().toString());
                isValid = MainActivity.updateData(((short)xbee));
                try {
                    Thread.currentThread().sleep(300);
                }
                catch (Exception e){

                }

                if(isValid){
                    Log.d("EF-BTBee", ">>roger that");
                }
                /*System.arraycopy(MainActivity.data,0,check,0,check.length);
                isValid = false;
                MainActivity.breaker = true;
                //Address Validity Check
                try {
                    Log.d("EF-BTBee", ">>data sending...");
                    MainActivity.sendData(((short)xbee));
                    Log.d("EF-BTBee", ">>roger that");
                    timer = new Timer();
                    timer.schedule(new MainActivity.Ender(), 1000);
                    while(MainActivity.breaker) {
                        for(int i = 0; i<check.length ; i++){
                            synchronized (MainActivity.data) {
                                if (check[i] != MainActivity.data[i]) {
                                    isValid = true;
                                    break;

                                }
                            }
                            if(isValid) {
                                break;
                            }
                            isValid = false;
                        }
                    }

                }
                catch(IOException e) {

                }*/
                if(last_name.equals("")||number<0||xbee<0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please use valid information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(!isValid){
                    Context context = getApplicationContext();
                    CharSequence text = "Not a valid address. \nMake sure the unit is on.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    MainActivity.xbees.add(new Short((short)xbee));
                    MainActivity.addPlayer(new Player(first_name, last_name, number, xbee));
                    MainActivity.sorted = false;
                    finish();
                }
            }
        });
    }

}
