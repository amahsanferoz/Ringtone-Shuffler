package com.arora.ringtoneshuffler;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listOfRingtones;
    private ToggleButton toggleShuffler;
    private List<Ringtone> ringtones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfRingtones = (ListView) findViewById(R.id.list_of_ringtones);
        toggleShuffler = (ToggleButton) findViewById(R.id.toggle);

        ringtones = RingtoneHelper.fetchAvailableRingtones(this);
        initializeList();
        initializeToggle();
    }

    private void initializeToggle() {
        final SharedPreferences preferences = getSharedPreferences("shuffler", Context.MODE_PRIVATE);
        boolean active = preferences.getBoolean("active", false);
        toggleShuffler.setChecked(active);

        toggleShuffler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences.edit().putBoolean("active", isChecked).commit();
            }
        });
    }

    private void initializeList() {
        ArrayAdapter<Ringtone> adapter = new ArrayAdapter<Ringtone>(this, android.R.layout.simple_list_item_1, ringtones) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setText(ringtones.get(position).getTitle(MainActivity.this));
                return item;
            }
        };
        listOfRingtones.setAdapter(adapter);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
