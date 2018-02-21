package com.voyager.sayaradriver.drivehelp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.voyager.sayaradriver.R;

/**
 * Created by User on 20-Feb-18.
 */

public class DriverHelp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Toolbar toolbarDriverHelp;
    Spinner enquiresSpinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_help);
        toolbarDriverHelp = (Toolbar) findViewById(R.id.toolbarDriverHelp);
        enquiresSpinner = (Spinner) findViewById(R.id.enquiresSpinner);

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
