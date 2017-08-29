package com.voyager.user.yourcab.LoginSignupPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.RegisterPage.RegisterPage;
import com.voyager.user.yourcab.SignInPage.SiginInPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSignIn(View v){
        Intent intent = new Intent(this, SiginInPage.class);
        startActivity(intent);
    }

    public  void btnSignUp(View v){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
}
