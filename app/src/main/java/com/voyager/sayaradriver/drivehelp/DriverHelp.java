package com.voyager.sayaradriver.drivehelp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.voyager.sayaradriver.R;

/**
 * Created by User on 20-Feb-18.
 */

public class DriverHelp extends AppCompatActivity {

    Toolbar toolbarDriverHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_help);
        toolbarDriverHelp = (Toolbar) findViewById(R.id.toolbarDriverHelp);

        setSupportActionBar(toolbarDriverHelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.driver_help_title));
        toolbarDriverHelp.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
