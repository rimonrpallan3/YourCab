package com.voyager.user.yourcab.SignInPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.RegisterPage.RegisterPage;

/**
 * Created by User on 8/23/2017.
 */

public class SiginInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
   }

   public void btnSubmit(View v){

   }

    public void SignUp(View v){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
        finish();
    }
}
