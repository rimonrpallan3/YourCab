package com.voyager.sayaradriver.photoCertificate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    Helper helper;
    String path;


    public static int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static int MEDIA_TYPE_IMAGE = 101;
    public static int MEDIA_TYPE_VIDEO = 2;



    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Driver Documents";

    public static String PACKAGE_NAME;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_certificate);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        bundle = getIntent().getExtras();
        methodName = bundle.getInt("METHOD_NAME");
        System.out.println("onCreate_methodName : "+methodName);
    }

    public void tkPhoto(View v){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            File photoFilePath = Environment
                    .getExternalStoragePublicDirectory(String.valueOf(Environment.getExternalStoragePublicDirectory("com.voyager.user.pluginmethod")));
            File imageFile = new File(String.valueOf(photoFilePath));
            Uri imageFileUri = Uri.fromFile(imageFile);

            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            camera_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
            startActivityForResult(camera_intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            dispatchTakePictureIntent();
        }

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
        System.out.println("mCurrentPhotoPath--------------"+mCurrentPhotoPath);
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
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        System.out.println("packageName--------------"+packageName);
                        grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }

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
        System.out.println("captureImage_fileUri---------------------"+fileUri);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
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
    private static File getOutputMediaFile(int type) {

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
            System.out.println("MEDIA_TYPE_IMAGE---------------------"+mediaFile);
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                if (mCurrentPhotoPath != null) {
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

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                if (mCurrentPhotoPath != null) {
                    // setPic();
                    galleryAddPic();
                    mCurrentPhotoPath = null;
                    System.out.println("onActivityResult_methodName : "+methodName);
                    Intent intent=new Intent();
                    intent.putExtra("METHOD_NAME",methodName);
                    setResult(methodName,intent);
                    finish();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

}