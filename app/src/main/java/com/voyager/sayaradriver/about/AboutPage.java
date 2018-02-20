package com.voyager.sayaradriver.about;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.voyager.sayaradriver.R;

/**
 * Created by User on 20-Feb-18.
 */

public class AboutPage  extends AppCompatActivity {

    Toolbar toolbarAboutPage;

    TextView tvAboutContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        toolbarAboutPage = (Toolbar) findViewById(R.id.toolbarAboutPage);

        setSupportActionBar(toolbarAboutPage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.about_title));
        toolbarAboutPage.setTitleTextColor(ContextCompat.getColor(this, R.color.black));
        tvAboutContent= (TextView)findViewById(R.id.tvAboutContent);

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