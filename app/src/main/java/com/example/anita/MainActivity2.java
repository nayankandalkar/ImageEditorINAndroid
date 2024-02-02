package com.example.anita;

import static com.example.anita.MainActivity.imagruri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
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
public class MainActivity2 extends AppCompatActivity {
///ImageView imageView=findViewById(R.id.imageView);
    ImageView imageView;
    boolean doublePress = false;
    Button btnCreate;
    public final int REQUEST_CODE = 100;
    int pageWidth = 720;
    int pageHeight = 1200;
    Bitmap imageBitmap, scaledImageBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         imageView=findViewById(R.id.imageView);
     //   Toast.makeText(this,imagruri.toString(), Toast.LENGTH_SHORT).show();
        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);

        dsPhotoEditorIntent.setData(imagruri);
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "YOUR_OUTPUT_IMAGE_FOLDER");

      //  int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};

    //    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);


        startActivityForResult(dsPhotoEditorIntent, 200);

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ramimg);
        scaledImageBitmap = Bitmap.createScaledBitmap(imageBitmap, 720, 257, false);



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
       ImageView imageView=findViewById(R.id.imageView);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 200:

                    Uri outputUri = data.getData();

                    // handle the result uri as you want, such as display it in an imageView;

                    //ImageView imageView;

                    imageView.setImageURI(outputUri);

                    break;

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       // switch ( item.getItemId()){
          //  case  R.id.share:
                BitmapDrawable drawable=  (BitmapDrawable)imageView.getDrawable();
                Bitmap bitmap=drawable.getBitmap();
                String bitymapPath= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"titl",null);
                Uri  uri=Uri.parse(bitymapPath);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                intent.putExtra(Intent.EXTRA_TEXT,"Playstore Link :https://play.google.com/store/apps/details?id="+getPackageName());
                startActivity(Intent.createChooser(intent,"share"));
              ///  break;
       // }
        return super.onOptionsItemSelected(item);
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