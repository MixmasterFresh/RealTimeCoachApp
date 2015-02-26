package com.example.austin.realtimecoach;

import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.app.Activity;

/**
 * Created by Austin on 2/26/2015.
 */
public class UserItemAdapter extends ArrayAdapter<Player> {
    private ArrayList<Player> players;
    private Context context;
    public UserItemAdapter(Context context, int textViewResourceId, ArrayList<Player> players) {
        super(context, textViewResourceId, players);
        this.players = players;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.rtc_line_layout, null);
        }

        Player player = players.get(position);
        if (player != null) {
            TextView first_name = (TextView) v.findViewById(R.id.first_display);
            TextView last_name = (TextView) v.findViewById(R.id.last_display);
            TextView number = (TextView) v.findViewById(R.id.number_display);
            TextView bpm = (TextView) v.findViewById(R.id.bpm_display);

            if (first_name != null) {
                first_name.setText(player.first_name);
            }

            if(last_name != null) {
                last_name.setText(player.last_name);
            }

            if(bpm != null) {
                bpm.setText(String.valueOf(player.heartRate));
            }

            if(number != null) {
                number.setText(String.valueOf(player.number));
            }
        }
        return v;
    }
}

