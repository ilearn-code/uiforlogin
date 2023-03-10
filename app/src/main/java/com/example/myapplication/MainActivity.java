package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button LoginButton;
TextView dispay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

LoginButton=(Button)findViewById(R.id.button);
dispay=(TextView)findViewById(R.id.textView4) ;
     LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeVolleyRequest("admin@gmail.com", "123456");


            }
        });

    }

    private void makeVolleyRequest(String username, String password) {

        String url = "https://pg.imbuetech.in/api/login"; // replace with your actual API endpoint

        // create the request parameters
        Map<String, String> params = new HashMap<>();
        params.put("email", username);
        params.put("password", password);

        // create the request object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle the response JSON object here
//                        Log.d("API Response", response);
                        Intent intent=new Intent(MainActivity.this,opt.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
//                        Toast.makeText(MainActivity.this, "heyy", Toast.LENGTH_LONG).show();

                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
}




