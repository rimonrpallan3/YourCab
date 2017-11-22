package com.voyager.sayaradriver.firstotppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.presenter.FirstOTPPresenter;
import com.voyager.sayaradriver.firstotppage.view.IFirstOTPView;
import com.voyager.sayaradriver.otppagesubmit.SubmitOTPPage;

/**
 * Created by User on 8/30/2017.
 */

 public class FirstOTPPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener,IFirstOTPView {

    FirstOTPPresenter otpPresenter;
    TextView edtZipCode;
    EditText edtPhNo;
    Button btnGetOtp;
    Spinner spinnerSelectContry;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page_first);
        edtZipCode = (TextView) findViewById(R.id.edtZipCode);
        edtPhNo = (EditText) findViewById(R.id.edtPhNo);
        btnGetOtp = (Button) findViewById(R.id.btnGetOtp);
        otpPresenter = new FirstOTPPresenter(this,this);

        spinnerSelectContry = (Spinner) findViewById(R.id.spinnerSelectCountry);
        spinnerSelectContry.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
    }

    public void btnGetOtp(View v){
        btnGetOtp.setEnabled(false);
        otpPresenter.doGetData(spinnerSelectContry.getSelectedItem().toString(),
                edtZipCode.getText().toString(),
                edtPhNo.getText().toString());
    }

    @Override
    public void validatedSendData(Boolean result, int code) {
        edtZipCode.setEnabled(true);
        edtPhNo.setEnabled(true);
        btnGetOtp.setEnabled(true);
        if (result) {
            Intent intent = new Intent(FirstOTPPage.this, SubmitOTPPage.class);
            intent.putExtra("Country",spinnerSelectContry.getSelectedItem().toString());
            intent.putExtra("ZipCode",edtZipCode.getText().toString());
            intent.putExtra("PhoneNo",edtPhNo.getText().toString());
            startActivity(intent);
            finish();
        } else {
            btnGetOtp.setEnabled(true);
            switch (code) {
                case -1:
                    Toast.makeText(this, "Please fill all the fields, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(this, "Please fill a valid Country Name, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(this, "Please fill a valid Zip Code, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -4:
                    Toast.makeText(this, "Please fill a valid Phone No, code = " + code, Toast.LENGTH_SHORT).show();
                    break;
                case -5:
                    Toast.makeText(this, "Phone number Should be Eight Digit, code = " + code, Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(this, "Please try Again Later, ", Toast.LENGTH_SHORT).show();
            }
        }
    }
 }
