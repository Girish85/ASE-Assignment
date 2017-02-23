package com.example.usgir.lab5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public File stg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onbclick(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imgname="userlab5.jpg";
        File imgfile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),imgname);
        Uri temp=Uri.fromFile(imgfile);
        stg=imgfile;
        intent.putExtra(MediaStore.EXTRA_OUTPUT,temp);
        startActivity(intent);
    }
    public void onsclick(View view){
        Intent intent1=new Intent(this,MapsActivity.class);
        intent1.putExtra("uri",stg);
        startActivity(intent1);
    }
}
