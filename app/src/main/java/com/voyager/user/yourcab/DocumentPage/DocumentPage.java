package com.voyager.user.yourcab.DocumentPage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.common.Helper;
import com.voyager.user.yourcab.photoCertificate.PhotoCertificate;
import com.voyager.user.yourcab.photoDoc.PhotoDoc;
import com.voyager.user.yourcab.photoLiciense.PhotoLiciense;
import com.voyager.user.yourcab.registersuccess.RegisterSuccessPage;

/**
 * Created by rimon on 1/9/17.
 */

public class DocumentPage extends AppCompatActivity  {

    Drawable icDone;
    Bundle bundle;
    TextView driverLicenseFront;
    TextView driverLicenseBack;
    TextView certificateRegister;
    TextView vehicleRegister;
    TextView vehiclePermit;
    TextView commercialInsurance;
    TextView taxReceipt;
    TextView contractCarriagePermit;
    TextView insuranceCertification;
    Boolean BdriverLicenseFront =false;
    Boolean BdriverLicenseBack =false;
    Boolean BcertificateRegister =false;
    Boolean BvehicleRegister =false;
    Boolean BvehiclePermit =false;
    Boolean BcommercialInsurance =false;
    Boolean BtaxReceipt =false;
    Boolean BcontractCarriagePermit =false;
    Boolean BinsuranceCertification =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_page);
        icDone = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_done);
        bundle = new Bundle();
        driverLicenseFront = (TextView) findViewById(R.id.driverLicenseFront);
        driverLicenseBack = (TextView) findViewById(R.id.driverLicenseBack);
        insuranceCertification = (TextView) findViewById(R.id.insuranceCertification);
        certificateRegister = (TextView) findViewById(R.id.certificateRegister);
        vehicleRegister = (TextView) findViewById(R.id.vehicleRegister);
        vehiclePermit = (TextView) findViewById(R.id.vehiclePermit);
        commercialInsurance = (TextView) findViewById(R.id.commercialInsurance);
        taxReceipt = (TextView) findViewById(R.id.taxReceipt);
        contractCarriagePermit = (TextView) findViewById(R.id.contractCarriagePermit);
        insuranceCertification = (TextView) findViewById(R.id.insuranceCertification);
    }

    public  void driverLicenseFront(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        bundle.putInt("METHOD_NAME",  Helper.DRIVER_LICENSE_FRONT);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.DRIVER_LICENSE_FRONT);
    }

    public  void driverLicenseBack(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        bundle.putInt("METHOD_NAME", Helper.DRIVER_LICENSE_BACK);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.DRIVER_LICENSE_BACK);
    }

    public  void certificateRegister(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoCertificate.class);
        bundle.putInt("METHOD_NAME", Helper.CERTIFICATE_REGISTER);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.CERTIFICATE_REGISTER);
    }

    public  void vehicleRegister(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        bundle.putInt("METHOD_NAME", Helper.VEHICLE_REGISTER);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.VEHICLE_REGISTER);
    }

    public  void vehiclePermit(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        bundle.putInt("METHOD_NAME", Helper.VEHICLE_PERMIT);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.VEHICLE_PERMIT);


        if (v.getId() == R.id.vehiclePermit) {
            // Do something when myButton was clicked
        }
    }

    public  void commercialInsurance(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        bundle.putInt("METHOD_NAME", Helper.COMMERCIAL_INSURANCE);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.COMMERCIAL_INSURANCE);
    }

    public  void taxReceipt(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoLiciense.class);
        bundle.putInt("METHOD_NAME", Helper.TAX_RECEPIT);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.TAX_RECEPIT);
    }

    public  void contractCarriagePermit(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoDoc.class);
        bundle.putInt("METHOD_NAME", Helper.CONTACT_CARRIAGE_PERMIT);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.CONTACT_CARRIAGE_PERMIT);
    }

    public  void insuranceCertification(View v){
        Intent intent = new Intent(DocumentPage.this, PhotoCertificate.class);
        bundle.putInt("METHOD_NAME", Helper.INSURANCE_CERTIFICATION);
        intent.putExtras(bundle);
        startActivityForResult(intent, Helper.INSURANCE_CERTIFICATION);
    }

    public  void contSubmit(View v){
        if(BdriverLicenseFront!=false&&
                BdriverLicenseBack!=false&&
                BcertificateRegister!=false&&
                BvehicleRegister!=false&&
                BcommercialInsurance!=false&&
                BvehiclePermit!=false&&
                BtaxReceipt!=false&&
                BcontractCarriagePermit!=false&&
                BinsuranceCertification!=false) {
            Intent intent = new Intent(DocumentPage.this, RegisterSuccessPage.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Please Submit All Documents", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult_requestCode : "+requestCode);
        // Check which request we're responding to
        if (requestCode == Helper.DRIVER_LICENSE_FRONT) {
            driverLicenseFront.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BdriverLicenseFront = true;
        }
        if (requestCode == Helper.DRIVER_LICENSE_BACK) {
            driverLicenseBack.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BdriverLicenseBack = true;
        }
        if (requestCode == Helper.CERTIFICATE_REGISTER) {
            certificateRegister.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BcertificateRegister = true;
        }
        if (requestCode == Helper.VEHICLE_REGISTER) {
            vehicleRegister.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BvehicleRegister = true;
        }
        if (requestCode == Helper.COMMERCIAL_INSURANCE) {
            commercialInsurance.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BcommercialInsurance = true;
        }
        if (requestCode == Helper.VEHICLE_PERMIT) {
            vehiclePermit.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BvehiclePermit = true;
        }
        if (requestCode == Helper.TAX_RECEPIT) {
            taxReceipt.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BtaxReceipt = true;
        }
        if (requestCode == Helper.CONTACT_CARRIAGE_PERMIT) {
            contractCarriagePermit.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BcontractCarriagePermit = true;
        }
        if (requestCode == Helper.INSURANCE_CERTIFICATION) {
            insuranceCertification.setCompoundDrawablesWithIntrinsicBounds(null,null,icDone,null);
            BinsuranceCertification = true;
        }
    }


}
