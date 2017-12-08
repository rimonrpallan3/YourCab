package com.voyager.sayaradriver.photoLiciense;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.voyager.sayaradriver.DocumentPage.model.DocModel;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.FileUtils;
import com.voyager.sayaradriver.common.Helper;
import com.voyager.sayaradriver.signinpage.model.UserModel;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 9/6/2017.
 */

public class PhotoLiciense extends AppCompatActivity {
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 2;
    int methodName;
    String TAG = "PhotoLiciense";

    private String selectedImagePath;

    private String filemanagerstring;
    Cursor cursor;

    Button galleryBtn;
    Button cameraBtn;
    Button cancelBtn;
    Activity activity;
    File Liciance;
    String driverId="";
    String docName="";
    String docType="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_liciense);
        activity =getParent();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            driverId = bundle.getString("driverId");
            docName = bundle.getString("DocName");
            methodName = bundle.getInt("METHOD_NAME");
            docType = bundle.getString("DocType");
            System.out.println("PhotoLiciense_onCreate_methodName : "+methodName);
        }
    }

    public void choosePhoto(View v){
        showDialog();
    }

    void showDialog(){
        final Dialog dialog = new Dialog(this);
        //create dialog without title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set the custom dialog's layout to the dialog
        dialog.setContentView(R.layout.custom_dialog);
        //set the background of dialog box as transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //display the dialog box
        dialog.show();

        //initializing views of custom dialog
        galleryBtn=(Button)dialog.findViewById(R.id.galleryBtn);
        cameraBtn = (Button)dialog.findViewById(R.id.cameraBtn);
        cancelBtn = (Button)dialog.findViewById(R.id.cancelBtn);


        //Typeface class specifies style of a font.
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Aller_Lt.ttf");
        //setting the font of some textviews and buttons
        galleryBtn.setTypeface(typeface);
        cameraBtn.setTypeface(typeface);
        cancelBtn.setTypeface(typeface);

        //dismiss the dialog and show toast on pressing the Login button
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
                dialog.dismiss();
               // Toast.makeText(PhotoLiciense.this,"You pressed galleryBtn button",Toast.LENGTH_LONG).show();
            }
        });

        //dismiss the dialog and show toast on pressing the Login button
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tkPhoto();
                dialog.dismiss();
              //  Toast.makeText(PhotoLiciense.this,"You pressed cameraBtn button",Toast.LENGTH_LONG).show();
            }
        });

        //dismiss the dialog and show toast on pressing the Login button
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               // Toast.makeText(PhotoLiciense.this,"You pressed cancelBtn button",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void tkPhoto(){
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

        }   else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
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

    public void choosePic(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/jpg");
        startActivityForResult(intent, Helper.SELECT_PICTURE);
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

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        try{
            String[] projection = { MediaStore.Images.Media.DATA };
            cursor = managedQuery(uri, projection, null, null, null);
            if( cursor != null ){
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                cursor.close();
                return path;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // this is our fallback here
        return uri.getPath();
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
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        String mImageName=docName+ driverId +".jpg";
        mediaFile = new File(storageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = docName + driverId;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        System.out.println("createImageFile : "+image.getName());
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
                try{
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.voyager.sayaradriver.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void uploadDoc(File file) {
        System.out.println("------- onActivityResult : mCurrentPhotoPath - " + mCurrentPhotoPath);
        RequestBody requestDriverId =
                RequestBody.create(MediaType.parse("text/plain"), driverId);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestDocType =
                RequestBody.create(MediaType.parse("text/plain"), docType);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("document_name", file.getName(), requestFile);

        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        if (mCurrentPhotoPath != null) {
            Call<DocModel> call = webServices.uploadFile(body, requestDriverId, requestDocType);
            call.enqueue(new Callback<DocModel>() {
                @Override
                public void onResponse(Call<DocModel> call,
                                       Response<DocModel> response) {
                    if (!response.body().error) {
                        Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                        mCurrentPhotoPath = null;
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
                    }

                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<DocModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("Upload error:", t.getMessage());
                    mCurrentPhotoPath = null;
                }
            });

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                if (mCurrentPhotoPath != null) {
                    // setPic();
                    File file = new File(mCurrentPhotoPath);
                    File newFile = new File(mCurrentPhotoPath,docName+driverId+".jpg");
                    file.renameTo(newFile);
                    System.out.println("onActivityResult_fileNmae : "+file.getName());
                    uploadDoc(file);
                    galleryAddPic();
                    mCurrentPhotoPath = null;
                    System.out.println("onActivityResult_methodName : "+methodName);
                    Intent intent=new Intent();
                    intent.putExtra("METHOD_NAME",methodName);
                    setResult(methodName,intent);
                    finish();
                }
            }

            if (requestCode == Helper.SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                File file = FileUtils.getFile(this, selectedImageUri);
                File newFile = new File(file.getAbsoluteFile(),docName+driverId+".jpg");
                file.renameTo(newFile);
                System.out.println("onActivityResult_fileNmae : "+file.getName());

                //OI FILE Manager
                filemanagerstring = selectedImageUri.getPath();

                //MEDIA GALLERY
                selectedImagePath = getPath(selectedImageUri);

                //DEBUG PURPOSE - you can delete this if you want
                if(selectedImagePath!=null)
                    mCurrentPhotoPath = selectedImagePath;
                else System.out.println("selectedImagePath is null");
                if(filemanagerstring!=null)
                mCurrentPhotoPath = filemanagerstring;
                else System.out.println("filemanagerstring is null");

                //NOW WE HAVE OUR WANTED STRING
                if(selectedImagePath!=null)
                    System.out.println("selectedImagePath is the right one for you!");
                else
                    System.out.println("filemanagerstring is the right one for you!");

                if(mCurrentPhotoPath!=null){
                    uploadDoc(file);
                    System.out.println("onActivityResult_methodName : "+methodName);
                    Intent intent=new Intent();
                    intent.putExtra("METHOD_NAME",methodName);
                    setResult(methodName,intent);
                    mCurrentPhotoPath = null;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Please try After SomeTimes", Toast.LENGTH_LONG)
                    .show();
        }

    }
}
