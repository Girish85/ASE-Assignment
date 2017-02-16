package com.example.usgir.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addlistener();
    }
        public void addlistener(){
    EditText e1 = (EditText) findViewById(R.id.editText);
    EditText e2 = (EditText) findViewById(R.id.editText2);
    Button btn = (Button) findViewById(R.id.button);
    btn.setOnClickListener(new View.OnClickListener()

    {
        public void onClick (View v){
        Intent inten=new Intent("com.example.usgir.login.Main2Activity");
        startActivity(inten);
    }
    });
    }
}

