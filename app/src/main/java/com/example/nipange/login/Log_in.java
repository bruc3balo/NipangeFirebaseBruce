package com.example.nipange.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nipange.R;
import com.example.nipange.landlord.dashboard_land;
import com.example.nipange.login.Sign_up;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Volley.URLS;

public class Log_in extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView register;
    Button button;


    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        backButton = findViewById(R.id.imageView3);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        email = (EditText)findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        register = (TextView) findViewById(R.id.textView7);
        button = findViewById(R.id.button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Sign_up.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }


    public void login(final View v) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLS.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Boolean error = object.getBoolean("error");
                            Log.e("ErrorValue", error.toString());

                            if(!error) {
                                Intent intent = new Intent(v.getContext(), dashboard_land.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(v.getContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}