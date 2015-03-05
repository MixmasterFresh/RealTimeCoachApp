package com.example.austin.realtimecoach;

/**
 * Created by Austin on 2/16/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

public class UpdatePlayerActivity extends Activity
{
    Player p=MainActivity.change;
    /* Get Default Adapter */
    private String first_name="";
    private String last_name="";
    private int number=-1;
    private int xbee=-1;
    //private EditText first_name_entry;
    //private EditText last_name_entry;
    //private EditText numberEntry;
    //private EditText xbeeEntry;
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
        first_name_entry.setText(p.first_name);
        last_name_entry.setText(p.last_name);
        numberEntry.setText(Integer.toString(p.number));
        xbeeEntry.setText(Integer.toString(p.xbee));
        button.setText("Update Player");
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
                if(last_name.equals("")||number<0||xbee<0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please use valid information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    MainActivity.players.set(MainActivity.spot,new Player(first_name,last_name,number,xbee));
                    finish();
                }
            }
        });
    }

}
