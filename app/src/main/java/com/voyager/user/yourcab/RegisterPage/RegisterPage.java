package com.voyager.user.yourcab.RegisterPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.SignInPage.SiginInPage;

/**
 * Created by User on 8/23/2017.
 */

public class RegisterPage  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
    }

    public void btnRegister(View v){

    }

    public void LogIn(View v){
        Intent intent = new Intent(this, SiginInPage.class);
        startActivity(intent);
        finish();
    }
}
