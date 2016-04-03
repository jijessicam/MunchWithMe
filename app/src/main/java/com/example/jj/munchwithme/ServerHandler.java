package com.example.jj.munchwithme;

/**
 * Created by JJ on 4/2/2016.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class ServerHandler extends StringRequest {

    // Server script goes here
    private static final String CREATE_ACTIVITY_URL = "http://munchwithme.comxa.com/create_activity.php";
    private Map<String, String> params;

    public ServerHandler(String time, String place, Response.Listener<String> listener) {
        super(Method.POST, CREATE_ACTIVITY_URL, listener, null);
        params = new HashMap<>();
        params.put("time", time);
        params.put("place", place);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
