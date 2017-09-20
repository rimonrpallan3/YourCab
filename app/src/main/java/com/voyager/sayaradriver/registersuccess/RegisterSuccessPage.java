package com.voyager.sayaradriver.registersuccess;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.registerapproved.RegisterApproved;

/**
 * Created by User on 9/7/2017.
 */

public class RegisterSuccessPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterSuccessPage.this, RegisterApproved.class);
                startActivity(intent);
                finish();
            }
        }, 5000);


    }

}
