package com.voyager.sayaradriver.photoCertificate;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.Helper;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 9/6/2017.
 */


public class PhotoCertificate extends AppCompatActivity {

    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 2;
    Bundle bundle;
    int methodName;
    private Uri fileUri; // file url to store image/video


    public static int MEDIA_TYPE_IMAGE = 101;
    public Uri imageFileUri;


    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Driver Documents";

    public static String PACKAGE_NAME;
    String TAG = "PhotoCertificate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_certificate);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        bundle = getIntent().getExtras();
        methodName = bundle.getInt("METHOD_NAME");
        System.out.println("onCreate_methodName : " + methodName);
    }

    public void tkPhoto(View v) throws IOException {
       if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
            File imageFile = new File(String.valueOf(CameraClick()));
            Uri imageFileUri = Uri.fromFile(imageFile);
            mCurrentPhotoPath = String.valueOf(imageFile.getAbsoluteFile());
            System.out.println("--------------KITKAT_imageFileUri" + mCurrentPhotoPath);
            if (imageFileUri != null) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(camera_intent, REQUEST_TAKE_PHOTO);
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

           if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                   if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                       Log.v(TAG,"Permission is granted");
                       //File write logic here
                       dispatchTakePictureIntent();
                   }else{
                       //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Helper.STORAGE_PERMISSION);
                       requestPermissions(new String[]{
                                       Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                       Manifest.permission.CAMERA},
                               Helper.CAMERA_STORAGE_PERMISSION);
                   }

           }else{
               dispatchTakePictureIntent();
           }

        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            switch (requestCode) {
                case Helper.CAMERA_STORAGE_PERMISSION:

                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.
                        dispatchTakePictureIntent();
                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this, "Permission denied to Access your Camera", Toast.LENGTH_SHORT).show();
                    }
                    break;
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

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
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
        System.out.println("mCurrentPhotoPath--------------" + mCurrentPhotoPath);
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /*
* Capturing Camera Image will lauch camera app requrest image capture
*/
    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        System.out.println("captureImage_fileUri---------------------" + fileUri);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    /*
     * Creating file uri to store image/video
	 */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
	 */
    private File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(String.valueOf(Environment.getExternalStoragePublicDirectory("com.voyager.user.pluginmethod"))),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {

            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
            mCurrentPhotoPath = String.valueOf(mediaFile.getAbsoluteFile());
            System.out.println("MEDIA_TYPE_IMAGE---------------------" + mediaFile);
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = null;
        String filePath = null;
        try {
            System.out.println("onActivityResult_Enter--resultCode-------------------" + requestCode);
            System.out.println("onActivityResult_Enter----data-----------------" + data);

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                if (mCurrentPhotoPath != null) {
                    // setPic();
                    selectedImageUri = imageFileUri;

                    System.out.println("onActivityResult---------------------" + mCurrentPhotoPath);
                    galleryAddPic();
                    mCurrentPhotoPath = null;
                    System.out.println("onActivityResult_methodName : " + methodName);
                    Intent intent = new Intent();
                    intent.putExtra("METHOD_NAME", methodName);
                    setResult(methodName, intent);
                    finish();
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, "Please try After SomeTimes", Toast.LENGTH_LONG)
                    .show();
        }
        if (selectedImageUri != null) {
            try {
                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(getApplicationContext(), "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {

             /*       Toast.makeText(getApplicationContext(), " path" + filePath,
                            Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                    // passing array index
                    i.putExtra("id", filePath);
                    startActivity(i);
*/
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }

    }

}