package com.voyager.sayaradriver.landingpage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 8/29/2017.
 */

 public class LandingPresenter implements ILandingPresenter{

    ILandingView iLandingView;
    Context context;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    String online = "";
    String offline = "";
    DriverUserModel driverUserModel;

    String fNmae ="";
    String pnoneNo ="";
    String pswd ="";
    String city ="";
    String country ="";
    String email ="";
    int driverId;
    String driverStatus = "";
    String AdminDriverStatus = "";



    public LandingPresenter(ILandingView iLandingView, DriverUserModel driverUserModel) {
        this.iLandingView = iLandingView;
        this.driverUserModel = driverUserModel;
        getDriverDetails();
    }

    @Override
    public void checkOfflineOnline(Boolean isChecked,CompoundButton buttonView) {
        online = context.getString(R.string.driver_online);
        offline = context.getString(R.string.driver_offline);
        System.out.println("driverSwitch Clicked"+"yehhh!!");
        if(AdminDriverStatus.equals("1")) {
            if (isChecked) {
                System.out.println("driverSwitch Clicked :" + " true!!");
                iLandingView.getOfflineOnlineState(online);
                driverUserModel.setDriverStatus("1");
                driverStatus = driverUserModel.getDriverStatus();
                uploadProfileName();
                //iLandingView.startService();
            } else {
                System.out.println("driverSwitch Clicked :" + " false!!");
                iLandingView.getOfflineOnlineState(offline);
                driverUserModel.setDriverStatus("0");
                driverStatus = driverUserModel.getDriverStatus();
                uploadProfileName();
                //iLandingView.stopService();
            }
        }else {
            buttonView.setChecked(false);
        }
    }

    private void getDriverDetails(){
        fNmae = driverUserModel.getFName().toString();
        pnoneNo =driverUserModel.getPhno().toString();
        pswd = driverUserModel.getPasswd().toString();
        city = driverUserModel.getCity().toString();
        country =  driverUserModel.getCountry().toString();
        email = driverUserModel.getEmail().toString();
        driverId = driverUserModel.getDriverId();
        AdminDriverStatus = driverUserModel.getAdiminDriverStatus();
    }

    private void addUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(driverUserModel);
        //DriverUserModel user1 = gson.fromJson(jsonString,DriverUserModel.class);
        if(jsonString!=null) {
            editor.putString("DriverUserModel", jsonString);
            editor.commit();
        }

    }



    public void uploadProfileName() {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<DriverUserModel> call = webServices.driverProfileStatus(driverId, driverStatus);
        call.enqueue(new Callback<DriverUserModel>() {
            @Override
            public void onResponse(Call<DriverUserModel> call,
                                   Response<DriverUserModel> response) {

                DriverUserModel user =response.body();
                System.out.println("----- uploadProfileName isError: "+user.isError +" user_error: "+user.error_msg );
                addUserGsonInSharedPrefrences();
            }

            @Override
            public void onFailure(Call<DriverUserModel> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("uploadProfileName error:", t.getMessage());
            }
        });

    }


}
