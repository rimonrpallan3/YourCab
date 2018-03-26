package com.voyager.sayaradriver.trackingid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.registerpage.RegisterPage;
import com.voyager.sayaradriver.trackingdetails.TrackingDetail;

/**
 * Created by rimon on 26-10-2017.
 */

public class TrackingID extends AppCompatActivity implements DialogInterface.OnClickListener {

    Drawable icDone;
    Bundle bundle;
    EditText edtTrackID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_id);
        bundle = new Bundle();

        edtTrackID = (EditText) findViewById(R.id.edtTrackID);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult_requestCode : " + requestCode);

    }

    public void btnTrackSubmit(View v){
        Intent intent = new Intent(this, TrackingDetail.class);
        startActivity(intent);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}

