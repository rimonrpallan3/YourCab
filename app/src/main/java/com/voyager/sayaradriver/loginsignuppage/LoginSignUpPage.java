package com.voyager.sayaradriver.loginsignuppage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.FirstOTPPage;
import com.voyager.sayaradriver.registerpage.RegisterPage;
import com.voyager.sayaradriver.signinpage.SignInPage;

public class LoginSignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sigup_page);
    }

    public void btnSignIn(View v){
        Intent intent = new Intent(this, SignInPage.class);
        startActivity(intent);
    }

    public  void btnSignUp(View v){
        Intent intent = new Intent(this, FirstOTPPage.class);
        startActivity(intent);
    }
    public  void btnTrack(View v){
        /*Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);*/
    }
}
