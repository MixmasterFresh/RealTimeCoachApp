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


public class MainActivity extends Activity {
    /* Get Default Adapter */
    private BluetoothAdapter  _bluetooth = BluetoothAdapter.getDefaultAdapter();
    private int counter=0;
    public ArrayList<Player> players = new ArrayList<>();
    /* request BT enable */
    private static final int  REQUEST_ENABLE      = 0x1;
    /* request BT discover */
    private static final int  REQUEST_DISCOVERABLE  = 0x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEnableButtonClicked()
    {
        //Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //startActivityForResult(enabler, REQUEST_ENABLE);
        //enable
        Button btn = (Button) findViewById(R.id.action_add_BT);

        if(isBluetoothEnabled())
        {
            _bluetooth.disable();
            btn.setEnabled(false);
        }
        else
        {
            _bluetooth.enable();
            if(counter==0)
            {
                Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(enabler, REQUEST_DISCOVERABLE);
                btn.setEnabled(true);
            }
            counter++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.action_add_BT:
                onEnableButtonClicked();
                return true;
            case R.id.action_toggle_BT:
                Intent enabler = new Intent(this, DiscoveryActivity.class);
                startActivity(enabler);
                return true;
            case R.id.action_add_player:
                Intent addPlayer = new Intent(this, AddPlayerActivity.class);
                startActivity(addPlayer);
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //return super.onOptionsItemSelected(item);
    }
    public boolean isBluetoothEnabled()
    {
        return _bluetooth.isEnabled();
    }
}
