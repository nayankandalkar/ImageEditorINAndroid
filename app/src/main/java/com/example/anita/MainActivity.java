package com.example.anita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {
  public  static Uri imagruri;
  ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
       //Button button1=findViewById(R.id.button3);
        imageView=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
                ImagePicker.Companion.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                          .start();
            }
        });

        ImagePicker.Companion.with(MainActivity.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .start();


}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
   try{
            imagruri=data.getData();
            if (!imagruri.equals(""))
              startActivity(new Intent(MainActivity.this,MainActivity2.class));}
   catch (Exception e) {
           e.printStackTrace();
   }
    //imageView=findViewById(R.id.imageView);
    }



    public  void  share(View view){
        BitmapDrawable drawable=  (BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        String bitymapPath= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"titl",null);
        Uri  uri=Uri.parse(bitymapPath);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.putExtra(Intent.EXTRA_TEXT,"Playstore Link :https://play.google.com/store/apps/details?id="+getPackageName());
        startActivity(Intent.createChooser(intent,"share"));
    }






}