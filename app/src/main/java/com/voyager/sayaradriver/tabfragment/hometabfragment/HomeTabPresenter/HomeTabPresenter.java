package com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter;

import android.util.Log;

import com.voyager.sayaradriver.tabfragment.hometabfragment.model.TripDetails;
import com.voyager.sayaradriver.tabfragment.hometabfragment.view.IHometabView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 14-Mar-18.
 */

public class HomeTabPresenter implements IHomeTabPresenter{

    IHometabView iHometabView;
    TripDetails tripDetails;

    public HomeTabPresenter(IHometabView iHometabView) {
        this.iHometabView =iHometabView;
    }


    @Override
    public void acceptTrip(int driverId,String tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverAcceptTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.acceptTrip();
                System.out.println("HomeTabPresenter----- rejectTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- rejectTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });

    }

    @Override
    public void rejectTrip(int driverId,String tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverRejectTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.rejectTrip();
                System.out.println("HomeTabPresenter----- rejectTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- rejectTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });
    }
}
