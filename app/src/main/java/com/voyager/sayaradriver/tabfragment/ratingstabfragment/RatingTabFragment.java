package com.voyager.sayaradriver.tabfragment.ratingstabfragment;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.MapDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/8/2017.
 */

public class RatingTabFragment  extends Fragment implements View.OnClickListener{


    List<MapDetails> mapDetailsList;


    private Activity activity;
    ImageView starImg1;
    ImageView starImg2;
    ImageView starImg3;
    ImageView starImg4;
    Drawable mDrawable;


    public RatingTabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.rate_tab_fragment, container, false);
        System.out.println("RatingTabFragment");
        starImg1 = (ImageView) rootView.findViewById(R.id.starImg1);
        starImg2 = (ImageView) rootView.findViewById(R.id.starImg2);
        starImg3 = (ImageView) rootView.findViewById(R.id.starImg3);
        starImg4 = (ImageView) rootView.findViewById(R.id.starImg4);
        starImg1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_star));
        starImg2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_star));
        starImg3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_star));
        starImg4.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_star));

        starImg1.setOnClickListener(this);
        starImg2.setOnClickListener(this);
        starImg3.setOnClickListener(this);
        starImg4.setOnClickListener(this);




        return rootView;
    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private List<MapDetails> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        mapDetailsList = new ArrayList<>();
        mapDetailsList.add(new MapDetails());
        return mapDetailsList;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.starImg1:
                mDrawable.setColorFilter(new
                PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));
                starImg1.setImageDrawable(mDrawable);
                break;
            case R.id.starImg2:
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));
                starImg2.setImageDrawable(mDrawable);
                break;
            case R.id.starImg3:
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));
                starImg3.setImageDrawable(mDrawable);
                break;
            case R.id.starImg4:
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));
                starImg4.setImageDrawable(mDrawable);
                break;

        }
    }
}