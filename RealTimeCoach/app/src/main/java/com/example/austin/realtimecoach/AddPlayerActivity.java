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
/**
 * Created by Austin on 2/11/2015.
 */
public class AddPlayerActivity extends Activity {
    /* Get Default Adapter */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
    }

    public void onEnableButtonClicked()
    {
    }
}
