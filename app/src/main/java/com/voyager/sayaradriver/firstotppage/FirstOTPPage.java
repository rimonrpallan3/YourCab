package com.voyager.sayaradriver.firstotppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.presenter.OTPPresenter;
import com.voyager.sayaradriver.otppagesubmit.SubmitOTPPage;

/**
 * Created by User on 8/30/2017.
 */

 public class FirstOTPPage extends AppCompatActivity  {

    OTPPresenter otpPresenter;
    EditText edtSelectContry;
    EditText edtZipCode;
    EditText edtPhNo;
    CheckBox checkTermsAndConductionBox;
    Button btnGetOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page_first);
        edtSelectContry = (EditText) findViewById(R.id.edtSelectContry);
        edtZipCode = (EditText) findViewById(R.id.edtZipCode);
        edtPhNo = (EditText) findViewById(R.id.edtPhNo);
        btnGetOtp = (Button) findViewById(R.id.btnGetOtp);
       // otpPresenter = new OTPPresenter(this,this);
     //   otpPresenter.setOPTSecondMsg(optSecondMsg);
    }




    public void btnGetOtp(View v){
        Intent intent = new Intent(FirstOTPPage.this, SubmitOTPPage.class);
        startActivity(intent);
        finish();
    }
 }
