package com.voyager.sayaradriver.tabfragment.ratingstabfragment;

import android.app.Activity;
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


    ImageView starImg1;
    ImageView starImg2;
    ImageView starImg3;
    ImageView starImg4;

    Boolean imgCheck1 = false;
    Boolean imgCheck2 = false;
    Boolean imgCheck3 = false;
    Boolean imgCheck4 = false;

    int rgb =1;


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

        rgb = ContextCompat.getColor(getContext(), R.color.yellow);




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
                if(imgCheck1 != true ){
                    starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    imgCheck1 = true;
                }else if(imgCheck1 != false){
                starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                imgCheck1 = false;
                imgCheck2 = false;
                imgCheck3 = false;
                imgCheck4 = false;

                }
                break;
            case R.id.starImg2:
                if(imgCheck2 != true ){
                    starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    imgCheck1 = true;
                    imgCheck2 = true;
                }else if(imgCheck2 != false){
                    starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    imgCheck2 = false;
                    imgCheck3 = false;
                    imgCheck4 = false;
                }
                break;
            case R.id.starImg3:
                if(imgCheck3 != true ){
                    starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    imgCheck1 = true;
                    imgCheck2 = true;
                    imgCheck3 = true;
                }else if(imgCheck3 != false){
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    imgCheck3 = false;
                    imgCheck4 = false;
                }
                break;
            case R.id.starImg4:
                if(imgCheck4 != true ){
                    starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow));
                    imgCheck1 = true;
                    imgCheck2 = true;
                    imgCheck3 = true;
                    imgCheck4 = true;
                }else if(imgCheck4 != false){
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                    imgCheck4 = false;
                }
                break;

        }
    }
}