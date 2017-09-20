package com.voyager.sayaradriver.otppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.sayaradriver.DocumentPage.DocumentPage;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.TermsAndConduction.TermsAndConduction;
import com.voyager.sayaradriver.otppage.presenter.OTPPresenter;
import com.voyager.sayaradriver.otppage.view.IOTPView;

/**
 * Created by User on 8/30/2017.
 */

 public class OTPPage extends AppCompatActivity implements IOTPView{

    OTPPresenter otpPresenter;
    TextView optSecondMsg;
    EditText edtOPTNo;
    CheckBox checkTermsAndConductionBox;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page);
        optSecondMsg = (TextView) findViewById(R.id.optSecondMsg);
        edtOPTNo = (EditText) findViewById(R.id.edtOPTNo);
        checkTermsAndConductionBox = (CheckBox) findViewById(R.id.checkTermsAndConductionBox);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        otpPresenter = new OTPPresenter(this,this);
        otpPresenter.setOPTSecondMsg(optSecondMsg);
    }

/*
    @Override
    public void moveToDocPage() {
        Intent intent = new Intent(OTPPage.this, DocumentPage.class);
        startActivity(intent);
        finish();
    }
*/

    @Override
    public void onSubmit(Boolean result, int code) {
        edtOPTNo.setEnabled(true);
        btnSubmit.setEnabled(true);
        if (result) {
            Intent intent = new Intent(this, DocumentPage.class);
            startActivity(intent);
            finish();
        } else {
            btnSubmit.setEnabled(true);
            switch (code) {
                case -1:
                    Toast.makeText(this, "Please type in OTP, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(this, "Please Agree the Terms and Conduction, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, "Please try Again Later, code = " + code, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void moveToTermsAndConductionPage() {
        Intent intent = new Intent(OTPPage.this, TermsAndConduction.class);
        startActivity(intent);
    }

    public void btnSubmit(View v){
        edtOPTNo.setEnabled(false);
        btnSubmit.setEnabled(false);
        otpPresenter.doOTPValidationAndCheck(edtOPTNo.getText().toString(),checkTermsAndConductionBox.isChecked());
    }
 }
