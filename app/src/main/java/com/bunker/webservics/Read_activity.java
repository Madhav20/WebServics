package com.bunker.webservics;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Read_activity extends AppCompatActivity
{
    ListView dy_list;
    String url="https://myfirstwebforandroid.000webhostapp.com/getdata.php";
     RequestQueue requestQueue;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {super.onCreate(savedInstanceState);
        setContentView(R.layout.read_act);


        dy_list=findViewById(R.id.dynamic_list);
        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,arrayList);

        dy_list.setAdapter(arrayAdapter);

        requestQueue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>()
            {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray jArray=jobj.getJSONArray("result");
                    for (int i=0;i<jArray.length();i++)
                    {
                       JSONObject obj= jArray.getJSONObject(i);
                        String name= obj.getString("name");
                        String email= obj.getString("email");
                        String phone= obj.getString("phone");
                        String pass= obj.getString("pass");
                        String address= obj.getString("address");

                        arrayList.add("Name:-"+name+"\n"+
                                "Email:-"+email+"\n"+
                                "Phone:-"+phone+"\n"+
                                "Password:-"+pass+"\n"+
                                "Address:-"+address);
                        arrayAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e)
                {

                }
                Toast.makeText(Read_activity.this, ""+response, Toast.LENGTH_SHORT).show();
            }},
            new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
        {
            Toast.makeText(Read_activity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        }}
        );
        requestQueue.add(request);
    }
}
