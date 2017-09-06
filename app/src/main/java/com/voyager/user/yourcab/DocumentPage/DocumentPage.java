package com.voyager.user.yourcab.DocumentPage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.otppage.OTPPage;
import com.voyager.user.yourcab.photoCertificate.PhotoCertificate;
import com.voyager.user.yourcab.photoDoc.PhotoDoc;
import com.voyager.user.yourcab.photoLiciense.PhotoLiciense;

/**
 * Created by rimon on 1/9/17.
 */

public class DocumentPage extends AppCompatActivity  {

    Drawable icDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_page);
        icDone = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_done);
    }

    public  void driverLicenseFront(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        startActivity(intent);
        Button driverLicenseFront =v.findViewById(R.id.driverLicenseFront);

        //driverLicenseFront.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void driverLicenseBack(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        startActivity(intent);

        Button driverLicenseBack =v.findViewById(R.id.driverLicenseBack);

        //driverLicenseBack.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void certificateRegister(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoCertificate.class);
        startActivity(intent);


        Button certificateRegister =v.findViewById(R.id.certificateRegister);

        //certificateRegister.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void vehicleRegister(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        startActivity(intent);

        Button vehicleRegister =v.findViewById(R.id.vehicleRegister);

        //vehicleRegister.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void vehiclePermit(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        startActivity(intent);

        Button vehiclePermit =v.findViewById(R.id.vehiclePermit);

        //vehiclePermit.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void commercialInsurance(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        startActivity(intent);

        Button commercialInsurance =v.findViewById(R.id.commercialInsurance);

        //commercialInsurance.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void taxReceipt(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        startActivity(intent);

        Button taxReceipt =v.findViewById(R.id.taxReceipt);

        //taxReceipt.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void contractCarriagePermit(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        startActivity(intent);

        Button contractCarriagePermit =v.findViewById(R.id.contractCarriagePermit);

        //contractCarriagePermit.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void insuranceCertification(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoCertificate.class);
        startActivity(intent);

        Button insuranceCertification =v.findViewById(R.id.insuranceCertification);

        //insuranceCertification.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }

    public  void contSubmit(View v){
        Intent intent = new Intent(DocumentPage.this, DocumentPage.class);
        startActivity(intent);

        Button certificateRegister =v.findViewById(R.id.certificateRegister);

        //certificateRegister.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
    }


}
