package com.voyager.sayaradriver.otppagesubmit;

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
import com.voyager.sayaradriver.otppagesubmit.presenter.OTPPresenter;
import com.voyager.sayaradriver.otppagesubmit.view.IOTPView;
import com.voyager.sayaradriver.registerpage.RegisterPage;

/**
 * Created by User on 8/30/2017.
 */

 public class SubmitOTPPage extends AppCompatActivity implements IOTPView{

    OTPPresenter otpPresenter;
    TextView optSecondMsg;
    EditText edtOPTNo;
    CheckBox checkTermsAndConductionBox;
    Button btnSubmit;
    String country ="";
    String zipCode ="";
    String PhoneNo ="";

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
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            country = bundle.getString("Country");
            zipCode = bundle.getString("ZipCode");
            PhoneNo = bundle.getString("PhoneNo");
        }
    }

    public void btnSubmit(View v){
        edtOPTNo.setEnabled(false);
        btnSubmit.setEnabled(false);
        otpPresenter.doOTPValidationAndCheck(edtOPTNo.getText().toString(),checkTermsAndConductionBox.isChecked());
    }

    @Override
    public void onSubmit(Boolean result, int code) {
        edtOPTNo.setEnabled(true);
        btnSubmit.setEnabled(true);
        if (result) {
            Intent intent = new Intent(this, RegisterPage.class);
            intent.putExtra("Country",country);
            intent.putExtra("ZipCode",zipCode);
            intent.putExtra("PhoneNo",PhoneNo);
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
        Intent intent = new Intent(SubmitOTPPage.this, TermsAndConduction.class);
        startActivity(intent);
    }


 }
