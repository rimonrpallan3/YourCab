package com.voyager.sayaradriver.firstotppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.presenter.OTPPresenter;
import com.voyager.sayaradriver.otppagesubmit.SubmitOTPPage;

/**
 * Created by User on 8/30/2017.
 */

 public class FirstOTPPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    OTPPresenter otpPresenter;
    EditText edtZipCode;
    EditText edtPhNo;
    Button btnGetOtp;
    Spinner spinnerSelectContry;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page_first);
        edtZipCode = (EditText) findViewById(R.id.edtZipCode);
        edtPhNo = (EditText) findViewById(R.id.edtPhNo);
        btnGetOtp = (Button) findViewById(R.id.btnGetOtp);
        //otpPresenter = new OTPPresenter(this,this);
        //otpPresenter.setOPTSecondMsg(optSecondMsg);

        spinnerSelectContry = (Spinner) findViewById(R.id.spinnerSelectCountry);
        spinnerSelectContry.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.country_names, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerSelectContry.setAdapter(adapter);
        spinnerSelectContry.setPrompt(getString(R.string.otp_spinner_country));

    }



    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView arg0) {
        // TODO Auto-generated method stub
        spinnerSelectContry.setPrompt(getString(R.string.otp_spinner_country));
       /* arg0.setAdapter(new NothingSelectedSpinnerAdapter(
                adapter,
                R.layout.contact_spinner_row_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));*/
    }

    public void btnGetOtp(View v){
        Intent intent = new Intent(FirstOTPPage.this, SubmitOTPPage.class);
        startActivity(intent);
        finish();
    }
 }
