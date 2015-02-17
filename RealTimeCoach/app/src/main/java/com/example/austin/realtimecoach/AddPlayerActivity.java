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
    private String name="";
    private int number=-1;
    private int xbee=-1;
    private EditText nameEntry;
    private EditText numberEntry;
    private EditText xbeeEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
        nameEntry=(EditText)findViewById(R.id.editText);
        numberEntry=(EditText)findViewById(R.id.editText2);
        xbeeEntry=(EditText)findViewById(R.id.editText3);
        final Button button = (Button) findViewById(R.id.final_add_player);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                name=nameEntry.toString();
                number=Integer.parseInt(numberEntry.toString());
                xbee=Integer.parseInt(xbeeEntry.toString());
                if(name.equals("")||number<0||xbee<0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please use valid information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    MainActivity.addPlayer(new Player(name,number,xbee));
                }
            }
        });
    }
}
