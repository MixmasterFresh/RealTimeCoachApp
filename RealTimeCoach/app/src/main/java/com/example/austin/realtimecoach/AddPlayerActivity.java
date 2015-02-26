package com.example.austin.realtimecoach;


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
import android.widget.Toast;
import android.content.Context;
import android.widget.EditText;

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
    private EditText first_name_entry;
    private EditText last_name_entry;
    private EditText numberEntry;
    private EditText xbeeEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
        first_name_entry=(EditText)findViewById(R.id.first);
        last_name_entry=(EditText)findViewById(R.id.last);
        numberEntry=(EditText)findViewById(R.id.number);
        xbeeEntry=(EditText)findViewById(R.id.address);
        final Button button = (Button) findViewById(R.id.final_add_player);
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
                    MainActivity.addPlayer(new Player(first_name,last_name,number,xbee));
                    finish();
                }
            }
        });
    }
}
