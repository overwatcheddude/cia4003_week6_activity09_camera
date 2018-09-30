package com.example.over.cia4003_week6_activity09_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private void displayMessage(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    //Global varaibles.
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    boolean camera;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onImageClick(View v)
    {
        camera = true;

        //launch camera app request image capture
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);//Requesting camera app to capture image
        startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);// start the image capture Intent
    }
    public void onVideoClick(View v)
    {
        camera = false;

        //launch camera app request image capture
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//Requesting camera app to capture image
        startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);// start the image capture Intent
    }
    //As we are starting an activity using startActivityForResult(),
    //we can expect some result in onActivityResult()
    //Receiving onActivityResult method will be called after closing the camera
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                if (camera)
                {
                    // successfully captured the image
                    // display it in image view
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    //get a reference to the imageview control
                    ImageView imageView = findViewById(R.id.imageView1);
                    imageView.setImageBitmap(photo);//display the photo in the imageview control
                }
            }
            else if (resultCode == RESULT_CANCELED)
            {
                displayMessage("User cancelled image capture");
            }
            else
                {
                    displayMessage("Sorry! Failed to capture image");
            }//end else
        }//end outer if
    }//end onActivityResult

    public void getImageFromGallery(View v)
    {
        
    }
}//end class MainActivity