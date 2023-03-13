package com.example.myapplication;

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
EditText emailed,passworded ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       LoginButton=(Button)findViewById(R.id.button);
       dispay=(TextView)findViewById(R.id.textView4) ;
       emailed=(EditText)findViewById(R.id.loginusername);

        passworded=(EditText)findViewById(R.id.loginPassword);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispay=(TextView)findViewById(R.id.textView4) ;

                String email = emailed.getText().toString(); // read the value of email from the EditText view
                String password = passworded.getText().toString().trim();
//                dispay.setText(password);
                makeVolleyRequest(email, password);


            }
        });

    }
    public void tv_reset(View view) {

        Intent intent=new Intent(MainActivity.this,registered_email.class);
        startActivity(intent);

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

                     Log.d("API Response", response.toString());
                      Intent intent=new Intent(MainActivity.this, otp.class);
                       startActivity(intent);
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error here
                      Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        // add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
}




