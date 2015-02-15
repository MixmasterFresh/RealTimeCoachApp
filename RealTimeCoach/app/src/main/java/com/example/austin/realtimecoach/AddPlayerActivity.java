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
/**
 * Created by Austin on 2/11/2015.
 */
public class AddPlayerActivity extends Activity
{

    /* Get Default Adapter */
    private String name="";
    private int number=-1;
    private int xbee=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
        final Button button = (Button) findViewById(R.id.final_add_player);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

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

                }
            }
        });
    }
}
