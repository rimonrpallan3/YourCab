package com.voyager.user.yourcab.registerapproved;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.voyager.user.yourcab.DocumentPage.DocumentPage;
import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.signinpage.SignInPage;


/**
 * Created by User on 9/7/2017.
 */

public class RegisterApproved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_approved_page);
    }

    public void btnSignIn(View v){
        Intent intent = new Intent(RegisterApproved.this, SignInPage.class);
        startActivity(intent);
        finish();

    }
}
