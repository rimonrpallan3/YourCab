package com.voyager.sayaradriver.displydoc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.profilepage.ProfileDetailPage;
import com.voyager.sayaradriver.profilepage.adapter.ProfileViewPageAdapter;

/**
 * Created by User on 20-Feb-18.
 */

public class DisplayDoc extends AppCompatActivity {

    Toolbar toolbarDisplayDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_doc);
        toolbarDisplayDoc = (Toolbar) findViewById(R.id.toolbarDisplayDoc);

        setSupportActionBar(toolbarDisplayDoc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.driver_profile_title));
        toolbarDisplayDoc.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

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
