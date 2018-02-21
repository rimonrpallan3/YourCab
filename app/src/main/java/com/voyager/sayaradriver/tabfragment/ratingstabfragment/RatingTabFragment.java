package com.voyager.sayaradriver.tabfragment.ratingstabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.NetworkPolicy;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.costom.CircleImageView;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.MapDetails;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.presenter.IRatingPresenter;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.presenter.RatingPresenter;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.view.IRatingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/8/2017.
 */

public class RatingTabFragment  extends Fragment implements View.OnClickListener,IRatingView{


    List<MapDetails> mapDetailsList;
    IRatingPresenter iRatingPresenter;
    DriverUserModel driverUserModel;

    String rating;
    String[] ratingArray;

    ImageView starImg1;
    ImageView starImg2;
    ImageView starImg3;
    ImageView starImg4;

    ImageView profileDriverImg;
    TextView profileDriverName;
    TextView driverProfileRating;

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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            driverUserModel = bundle.getParcelable("DriverUserModel");
            Gson gson = new Gson();
            String jsonString = gson.toJson(driverUserModel);
            System.out.println("-----------RatingTabFragment DriverUserModel"+jsonString);
        }
        profileDriverImg = (CircleImageView) rootView.findViewById(R.id.profileDriverImg);
        profileDriverName = (TextView) rootView.findViewById(R.id.profileDriverName);
        driverProfileRating = (TextView) rootView.findViewById(R.id.driverProfileRating);


        starImg1 = (ImageView) rootView.findViewById(R.id.starImg1);
        starImg2 = (ImageView) rootView.findViewById(R.id.starImg2);
        starImg3 = (ImageView) rootView.findViewById(R.id.starImg3);
        starImg4 = (ImageView) rootView.findViewById(R.id.starImg4);
        starImg1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_new));
        starImg2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_new));
        starImg3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_new));
        starImg4.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_new));

        starImg1.setOnClickListener(this);
        starImg2.setOnClickListener(this);
        starImg3.setOnClickListener(this);
        starImg4.setOnClickListener(this);

        rgb = ContextCompat.getColor(getContext(), R.color.yellow);
        iRatingPresenter = new RatingPresenter(this,driverUserModel);
        iRatingPresenter.loadData();
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
                starImg1.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
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
                    starImg2.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
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
                    starImg3.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                    starImg4.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
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

    @Override
    public void loadData(String driverName, String driverCity, String driverRating, final String driverPhoto, boolean error) {
        Picasso.with(getContext())
                .load(driverPhoto)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(0, 200)
                .into(profileDriverImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(driverPhoto)
                                .error(R.drawable.profile)
                                .resize(0, 200)
                                .into(profileDriverImg, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
        profileDriverName.setText(driverName);
        rating =(driverRating);
        ratingArray =rating.split("(?<=\\.\\d{1})");
        for (int i=0; i<(ratingArray.length); i++ ) {
            System.out.println("getCommentListAndDetails values :"+ ratingArray[i]);
        }
        driverProfileRating.setText(ratingArray[0]);

    }
}