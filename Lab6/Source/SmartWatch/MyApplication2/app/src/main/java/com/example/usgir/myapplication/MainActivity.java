package com.example.usgir.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);
    }
    public void onClick(View v)
    {
        String url1 = "https://api.edamam.com/search?q=chicken&app_id=42e47428&app_key=c4dcd279be1a311cc7f745f99ff17f42&from=0&to=1";
        RequestQueue requestQueue = newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name = response.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("label");
                    TextView v = (TextView) findViewById(R.id.textView);
                    String Source = response.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("source");
                    // String ingredients=response.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getJSONArray("ingredientLines").getJSONObject(0).toString();
                    v.setText("Name:"+name+"'\n'Source:"+Source);
                    String img= response.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("image");
                    ImageView v1 = (ImageView)findViewById(R.id.imageView);
                    Picasso.with(getApplicationContext()).load(img).into(v1);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "not possible Girish", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
