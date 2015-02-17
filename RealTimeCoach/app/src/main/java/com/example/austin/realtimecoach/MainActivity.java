package com.example.austin.realtimecoach;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.bluetooth.BluetoothServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.app.ListActivity;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ListActivity {
    /* Get Default Adapter */


    private static BluetoothAdapter  _bluetooth = BluetoothAdapter.getDefaultAdapter();
    public static ArrayList<Player> players = new ArrayList<>();
    /* request BT enable */
    private static final int  REQUEST_ENABLE      = 0x1;
    /* request BT discover */
    private static final int  REQUEST_DISCOVERABLE  = 0x2;
    static BluetoothDevice device;
    static ArrayList<Integer> xbees=new ArrayList<>();
    static Menu menu;
    static MenuItem item;
    static MenuItem item2;
    boolean iteme=false;
    boolean item2e=false;
    private static final String TAG = MainActivity.class.getSimpleName();
    static boolean checker=false;
    static BluetoothSocket socket;
    static OutputStream output;
    static InputStream input;
    static Thread workerThread;
    static byte[] readBuffer;
    static int readBufferPosition;
    static int counter;
    static volatile boolean stopWorker;
    static String data;
    private Handler _handler = new Handler();
    static Player change;
    static boolean breaker=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isBluetoothEnabled()&&checker)
        {
            try {
                establishConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showPlayers();
    }

    public void onEnableButtonClicked()
    {
        //Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //startActivityForResult(enabler, REQUEST_ENABLE);
        //enable

        if(isBluetoothEnabled())
        {
            try
            {
                closeBT();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            _bluetooth.disable();
            iteme=false;
            checker=false;
            item2e=false;
        }
        else
        {
            _bluetooth.enable();
            Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabler, 0);
            iteme=true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(isBluetoothEnabled())
        {
            iteme=true;
        }
        item = menu.findItem(R.id.action_add_BT);
        item.setEnabled(iteme);
        item2 = menu.findItem(R.id.action_add_player);
        item2.setEnabled(item2e);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.action_add_BT:
                Intent enabler = new Intent(this, DiscoveryActivity.class);
                startActivity(enabler);
                item2e=true;
                return true;
            case R.id.action_toggle_BT:
                onEnableButtonClicked();
                return true;
            case R.id.action_add_player:
                if(checker)
                {
                    Intent addPlayer = new Intent(this, AddPlayerActivity.class);
                    startActivity(addPlayer);
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please use a valid bluetooth connection!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //return super.onOptionsItemSelected(item);
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        change = players.get(position);
        Intent editor = new Intent(this, UpdatePlayerActivity.class);
        startActivity(editor);
    }
    public static void addPlayer(Player player)
    {
        players.add(player);
    }

    public boolean isBluetoothEnabled()
    {
        return _bluetooth.isEnabled();
    }

    public void establishConnection() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        socket = device.createRfcommSocketToServiceRecord(uuid);
        socket.connect();
        output = socket.getOutputStream();
        input = socket.getInputStream();

    }

    static void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character
        data="";
        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = input.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            counter=input.read(packetBytes);
                            for(int i=0;i<counter;i++)
                            {
                                try
                                {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                        data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;
                                    }
                                    else
                                    {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

    public static boolean isValidAddress(int x)
    {
        boolean check=false;
        xbees=new ArrayList<>();
        int lastCut=0;
        try
        {
            Timer timer=new Timer();
            sendData("5");
            beginListenForData();
            while(data.length()!=0&&breaker)
            {
                timer.schedule(new Ender(),1000);
            }
            timer.cancel();
            for(int i=0;i<data.length();i++)
            {
                if(data.charAt(i)==',')
                {
                    xbees.add(Integer.parseInt(data.substring(lastCut+1,i)));
                    lastCut=i;
                }
            }
            for(Integer value:xbees)
            {
                if(value.intValue()==x)
                {
                    check=true;
                }
            }
            breaker=true;
            return check;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return check;
        }
    }

    void closeBT() throws IOException
    {
        stopWorker = true;
        output.close();
        input.close();
        socket.close();
        Context context = getApplicationContext();
        CharSequence text = "Bluetooth Closed.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected boolean showPlayers()
    {
        List<String> list = new ArrayList<String>();
        for (int i = 0, size = players.size(); i < size; ++i)
        {
            StringBuilder b = new StringBuilder();
            Player d = players.get(i);
            b.append("#");
            b.append(d.number);
            b.append("  ");
            b.append(d.name);
            b.append('\n');
            b.append("Heart Rate: ");
            b.append(d.heartRate);
            b.append(" BPM");
            b.append('\n');
            b.append("Collision Severity: ");
            if(d.severity==1)
            {
                b.append("none");
            }
            if(d.severity==2)
            {
                b.append("Warning");
            }
            if(d.severity==3)
            {
                b.append("Severe");
            }
            else
            {
                b.append("ERROR");
            }
            String s = b.toString();
            list.add(s);
        }
        Log.d("EF-BTBee", ">>showDevices");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        _handler.post(new Runnable() {
            public void run()
            {
                setListAdapter(adapter);
            }
        });
        return true;
    }

    static void sendData(String data) throws IOException
    {
        output.write(data.getBytes());
    }

    static class Ender extends TimerTask {
        public void run() {
            breaker=false;
        }
    }
}