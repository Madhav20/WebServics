package com.bunker.webservics;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    EditText name,email,pass,address,phone;
    Button set_button,get_button;

    ProgressDialog pd;
    RequestQueue requestQueue;
    String url="https://myfirstwebforandroid.000webhostapp.com/setdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText_name);
        email = findViewById(R.id.editText_email);
        pass = findViewById(R.id.editText_pass);
        address = findViewById(R.id.editText_address);
        phone = findViewById(R.id.editText_phone);

        set_button = findViewById(R.id.button_set);
        get_button = findViewById(R.id.button_get);


        requestQueue = Volley.newRequestQueue(this);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please Wait");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response)
                            {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Details Saved..",
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "" + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }) {
                    //init block
                    //run before the constructor
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> mydata = new HashMap<String, String>();

                        mydata.put("name", name.getText().toString());
                        mydata.put("email", email.getText().toString());
                        mydata.put("phone", phone.getText().toString());
                        mydata.put("pass", pass.getText().toString());
                        mydata.put("address", address.getText().toString());

                        return mydata;
                    }
                };
                requestQueue.add(request);
            }
        });

        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Read_activity.class);
                startActivity(intent);
            }
        });

    }
}

