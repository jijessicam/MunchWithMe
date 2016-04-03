package com.example.jj.munchwithme;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Button;

import android.content.Intent;


import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response.Listener;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spin);
        // Create ArrayAdapter using 'locations' array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations,
                android.R.layout.simple_spinner_item);
        // Specify layout to use when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply adapter to spinner
        spinner.setAdapter(adapter);

        final String time = String.valueOf(findViewById(R.id.timePicker));
        final String place = String.valueOf(findViewById(R.id.spin));
        final Button makeEvent = (Button)findViewById(R.id.create_event_button);

        makeEvent.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Response.Listener<String> responseListener = new Response.Listener<String>() {
                                                 @Override
                                                 public void onResponse(String response) {
                                                     try {
                                                         JSONObject jsonObject = new JSONObject(response);
                                                         boolean success = jsonObject.getBoolean("success");
                                                         if (success) {
                                                             // Change this later when you get the feed
                                                             Intent intent = new Intent(CreateEvent.this, CreateEvent.class);
                                                             CreateEvent.this.startActivity(intent);
                                                         } else {
                                                             AlertDialog.Builder builder = new AlertDialog.Builder(CreateEvent.this);
                                                             builder.setMessage("Event creation failed")
                                                                     .setNegativeButton("Retry", null)
                                                                     .create()
                                                                     .show();
                                                         }
                                                     } catch (JSONException e) {
                                                         e.printStackTrace();
                                                     }
                                                 }
                                             };
                                             ServerHandler serverHandler = new ServerHandler(time, place, responseListener);
                                         }
                                     });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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


