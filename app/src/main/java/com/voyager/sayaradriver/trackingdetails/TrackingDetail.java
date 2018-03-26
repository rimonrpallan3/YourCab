package com.voyager.sayaradriver.trackingdetails;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.voyager.sayaradriver.R;

/**
 * Created by rimon on 26-10-2017.
 */


public class TrackingDetail extends AppCompatActivity {

    Drawable icDone;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_detail);
        bundle = new Bundle();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult_requestCode : " + requestCode);

    }
}
