package com.voyager.sayaradriver.photoDoc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.voyager.sayaradriver.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 9/6/2017.
 */

public class PhotoDoc extends AppCompatActivity {
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 2;
    ImageView docImg;
    Bundle bundle;
    int methodName;
    File photoFile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_doc);
        docImg =(ImageView) this.findViewById(R.id.docImg);
        bundle = getIntent().getExtras();
        methodName = bundle.getInt("METHOD_NAME");
        System.out.println("onCreate_methodName : "+methodName);

    }

    public void tkPhoto(View v){
       /* if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            File photoFilePath = Environment
                    .getExternalStoragePublicDirectory(String.valueOf(Environment.getExternalStoragePublicDirectory("com.voyager.sayaradriver")));
            File imageFile = new File(String.valueOf(photoFilePath));
            Uri imageFileUri = Uri.fromFile(imageFile);
            mCurrentPhotoPath = String.valueOf(imageFile.getAbsoluteFile());

            // Continue only if the File was successfully created
            if (imageFileUri != null) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(camera_intent, REQUEST_TAKE_PHOTO);
            }
        } else*/ if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            File imageFile = new File(String.valueOf(CameraClick()));
            Uri imageFileUri = Uri.fromFile(imageFile);
            mCurrentPhotoPath = String.valueOf(imageFile.getAbsoluteFile());
            System.out.println("--------------KITKAT_imageFileUri" + mCurrentPhotoPath);
            if (imageFileUri != null) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(camera_intent, REQUEST_TAKE_PHOTO);
            }

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            dispatchTakePictureIntent();
        }

    }

    private File CameraClick() {
        File storageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");


        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! storageDir.exists()){
            if (! storageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(storageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.voyager.sayaradriver.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = docImg.getWidth();
        int targetH = docImg.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
       // docImg.setImageBitmap(bitmap);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                if (mCurrentPhotoPath != null ) {
                   // setPic();
                    galleryAddPic();
                    mCurrentPhotoPath = null;
                    System.out.println("onActivityResult_methodName : "+methodName);
                    Intent intent=new Intent();
                    intent.putExtra("METHOD_NAME",methodName);
                    setResult(methodName,intent);
                    finish();
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, "Please try After SomeTimes", Toast.LENGTH_LONG)
                    .show();
        }

    }
}