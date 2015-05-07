package com.seven.austin.realtimecoach;

import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
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
        if (player != null && player.valid) {
            TextView first_name = (TextView) v.findViewById(R.id.first_display);
            TextView last_name = (TextView) v.findViewById(R.id.last_display);
            TextView number = (TextView) v.findViewById(R.id.number_display);
            TextView bpm = (TextView) v.findViewById(R.id.bpm_display);
            TextView bpmL = (TextView) v.findViewById(R.id.bpm_label);

            if (first_name != null) {
                first_name.setText(player.first_name);
            }

            if(last_name != null) {
                last_name.setText(player.last_name);
            }

            if(bpm != null) {
                bpm.setText(String.valueOf(player.heartRate));
                bpmL.setText("BPM");
            }

            if(number != null) {
                number.setText(String.valueOf(player.number));
            }

            ImageView img= (ImageView) v.findViewById(R.id.imageView);
            if(player.hseverity==1&&player.severity==1){
                img.setImageResource(R.drawable.check11);
            }
            else if(player.hseverity==1&&player.severity==2){
                img.setImageResource(R.drawable.check12);
            }
            else if(player.hseverity==1&&player.severity==3){
                img.setImageResource(R.drawable.check13);
            }
            else if(player.hseverity==2&&player.severity==1){
                img.setImageResource(R.drawable.check21);
            }
            else if(player.hseverity==2&&player.severity==2){
                img.setImageResource(R.drawable.check22);
            }
            else if(player.hseverity==2&&player.severity==3){
                img.setImageResource(R.drawable.check23);
            }
            else if(player.hseverity==3&&player.severity==1){
                img.setImageResource(R.drawable.check31);
            }
            else if(player.hseverity==3&&player.severity==2){
                img.setImageResource(R.drawable.check32);
            }
            else if(player.hseverity==3&&player.severity==3){
                img.setImageResource(R.drawable.check33);
            }
            //TODO: Picture time
        }
        else if(player != null) {
            TextView first_name = (TextView) v.findViewById(R.id.first_display);
            TextView last_name = (TextView) v.findViewById(R.id.last_display);
            TextView number = (TextView) v.findViewById(R.id.number_display);
            TextView bpm = (TextView) v.findViewById(R.id.bpm_display);
            TextView bpmL = (TextView) v.findViewById(R.id.bpm_label);

            if (first_name != null) {
                first_name.setText(player.first_name);
            }

            if(last_name != null) {
                last_name.setText(player.last_name);
            }

            bpm.setText("Connection");
            bpmL.setText("Lost");

            if(number != null) {
                number.setText(String.valueOf(player.number));
            }

            ImageView img= (ImageView) v.findViewById(R.id.imageView);
            if(player.hseverity==1&&player.severity==1){
                img.setImageResource(R.drawable.check11);
            }
            else if(player.hseverity==1&&player.severity==2){
                img.setImageResource(R.drawable.check12);
            }
            else if(player.hseverity==1&&player.severity==3){
                img.setImageResource(R.drawable.check13);
            }
            else if(player.hseverity==2&&player.severity==1){
                img.setImageResource(R.drawable.check21);
            }
            else if(player.hseverity==2&&player.severity==2){
                img.setImageResource(R.drawable.check22);
            }
            else if(player.hseverity==2&&player.severity==3){
                img.setImageResource(R.drawable.check23);
            }
            else if(player.hseverity==3&&player.severity==1){
                img.setImageResource(R.drawable.check31);
            }
            else if(player.hseverity==3&&player.severity==2){
                img.setImageResource(R.drawable.check32);
            }
            else if(player.hseverity==3&&player.severity==3){
                img.setImageResource(R.drawable.check33);
            }
            //TODO: Picture time
        }
        return v;
    }
}

