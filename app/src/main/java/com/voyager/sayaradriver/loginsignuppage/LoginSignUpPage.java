package com.voyager.sayaradriver.loginsignuppage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.FirstOTPPage;
import com.voyager.sayaradriver.signinpage.SignInPage;
import com.voyager.sayaradriver.trackingid.TrackingID;

public class LoginSignUpPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sigup_page);
    }

    public void btnSignIn(View v){
        Intent intent = new Intent(this, SignInPage.class);
        startActivityForResult(intent,1);
    }

    public  void btnSignUp(View v){
        Intent intent = new Intent(this, FirstOTPPage.class);
        startActivity(intent);
    }
    public  void btnTrack(View v){
        Intent intent = new Intent(this, TrackingID.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            finish();
        }
    }
}
