package com.voyager.sayaradriver.drivehelp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;

/**
 * Created by User on 20-Feb-18.
 */

public class DriverHelp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Toolbar toolbarDriverHelp;
    Spinner enquiresSpinner;
    ArrayAdapter<CharSequence> adapter;
    ImageView customerCareCall;
    ProfileModel profileModel;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_help);
        toolbarDriverHelp = (Toolbar) findViewById(R.id.toolbarDriverHelp);
        enquiresSpinner = (Spinner) findViewById(R.id.enquiresSpinner);
        customerCareCall = (ImageView) findViewById(R.id.customerCareCall);
        Intent intent = getIntent();
        bundle = new Bundle();
        profileModel = (ProfileModel) intent.getParcelableExtra("ProfileModel");
        if (profileModel != null) {
            System.out.println("ProfileDetailPage -- ProfileModel- driverId : " + profileModel.getDriverId());
        }

        setSupportActionBar(toolbarDriverHelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.driver_help_title));
        toolbarDriverHelp.setTitleTextColor(ContextCompat.getColor(this, R.color.black));
        enquiresSpinner.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.enquires, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        enquiresSpinner.setAdapter(adapter);


    }

    public void callCustomerCare(View v){
        System.out.println("callCustomerCare -- -  : " );
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + profileModel.getDriverPhone()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        enquiresSpinner.setPrompt(getString(R.string.driver_help_spinner_enquires));
    }
}
