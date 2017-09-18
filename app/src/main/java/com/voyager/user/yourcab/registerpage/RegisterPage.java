package com.voyager.user.yourcab.registerpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.otppage.OTPPage;
import com.voyager.user.yourcab.registerpage.presenter.RegisterPresenter;
import com.voyager.user.yourcab.registerpage.view.IRegisterView;
import com.voyager.user.yourcab.signinpage.SignInPage;

/**
 * Created by User on 8/23/2017.
 */

public class RegisterPage  extends AppCompatActivity implements IRegisterView{

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhoneNo;
    private EditText edtCity;
    private EditText edtCPR;
    private Button btnRegister;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //find view
        edtFirstName = (EditText) this.findViewById(R.id.edtFirstName);
        edtLastName = (EditText) this.findViewById(R.id.edtLastName);
        edtPhoneNo = (EditText) this.findViewById(R.id.edtPhoneNo);
        edtCity = (EditText) this.findViewById(R.id.edtCity);
        edtCPR = (EditText) this.findViewById(R.id.edtCPR);
        btnRegister = (Button) this.findViewById(R.id.btnRegister);

        //init
        registerPresenter = new RegisterPresenter(this);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void btnRegister(View v){
        btnRegister.setEnabled(false);
        registerPresenter.doRegister(edtFirstName.getText().toString(),
                edtLastName.getText().toString(),
                edtPhoneNo.getText().toString(),
                edtCity.getText().toString(),
                edtCPR.getText().toString());

    }

    public void LogIn(View v){
        Intent intent = new Intent(this, SignInPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegister(Boolean result, int code) {
        edtFirstName.setEnabled(true);
        edtLastName.setEnabled(true);
        edtPhoneNo.setEnabled(true);
        edtCity.setEnabled(true);
        edtCPR.setEnabled(true);
        if (result) {
            Intent intent = new Intent(this, OTPPage.class);
            startActivity(intent);
            finish();
        } else {
            btnRegister.setEnabled(true);
            switch (code) {
                case -1:
                    Toast.makeText(this, "Please fill all the fields, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(this, "Please fill a valid First Name, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(this, "Please fill a valid Last Name, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -4:
                    Toast.makeText(this, "Please fill a valid Phone No, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -5:
                    Toast.makeText(this, "Please fill a valid City Name, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -6:
                    Toast.makeText(this, "Please fill a valid CPR No, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, "Please try Again Later, code = " + code, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
