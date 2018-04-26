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
    String online = "";
    String offline = "";
    DriverUserModel driverUserModel;

    String fNmae ="";
    String pnoneNo ="";
    String pswd ="";
    String city ="";
    String country ="";
    String email ="";
    String CPR ="";
    String userName ="";
    String LName ="";
    int driverId;
    String driverStatus = "";
    String AdminDriverStatus = "";




    public LandingPresenter(ILandingView iLandingView, DriverUserModel driverUserModel,String online,String offline) {
        this.iLandingView = iLandingView;
        this.driverUserModel = driverUserModel;
        this.online = online;
        this.offline = offline;
        getDriverDetails();
    }

    @Override
    public void checkOfflineOnline(Boolean isChecked,CompoundButton buttonView) {
        System.out.println("driverSwitch Clicked"+"yehhh!!");
        if(AdminDriverStatus.equals("1")) {
            if (isChecked) {
                System.out.println("driverSwitch Clicked :" + " true!!");
                iLandingView.getOfflineOnlineState(online);
                driverUserModel.setDriverStatus("1");
                getDriverDetails();
                uploadProfileName();
                iLandingView.startService();
            } else {
                System.out.println("driverSwitch Clicked :" + " false!!");
                iLandingView.getOfflineOnlineState(offline);
                driverUserModel.setDriverStatus("0");
                getDriverDetails();
                uploadProfileName();
                iLandingView.stopService();
            }
        }else {
            buttonView.setChecked(false);
        }
    }

    private void getDriverDetails(){
        fNmae = driverUserModel.getFName().toString();
        LName = driverUserModel.getLName().toString();
        userName = driverUserModel.getUserName().toString();
        CPR = driverUserModel.getCPR().toString();
        pnoneNo = driverUserModel.getPhno().toString();
        pswd = driverUserModel.getPasswd().toString();
        city = driverUserModel.getCity().toString();
        country =  driverUserModel.getCountry().toString();
        email = driverUserModel.getEmail().toString();
        driverId = driverUserModel.getDriverId();
        AdminDriverStatus = driverUserModel.getAdiminDriverStatus();
        driverStatus = driverUserModel.getDriverStatus();
    }



    public void uploadProfileName() {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<DriverUserModel> call = webServices.driverProfileStatus(driverId, driverStatus);
        call.enqueue(new Callback<DriverUserModel>() {
            @Override
            public void onResponse(Call<DriverUserModel> call,
                                   Response<DriverUserModel> response) {
                driverUserModel =response.body();
                driverUserModel.setFName(fNmae);
                driverUserModel.setPhno(pnoneNo);
                driverUserModel.setPasswd(pswd);
                driverUserModel.setCountry(country);
                driverUserModel.setEmail(email);
                driverUserModel.setDriverId(driverId);
                driverUserModel.setLName(LName);
                driverUserModel.setCPR(CPR);
                driverUserModel.setUserName(userName);
                driverUserModel.setCity(city);
                driverUserModel.setDriverStatus(driverStatus);
                driverUserModel.setAdiminDriverStatus(AdminDriverStatus);
                System.out.println("LandingPresenter----- uploadProfileName isError: "+driverUserModel.isError +" user_error: "+driverUserModel.error_msg );
                iLandingView.addUserGsonInSharedPrefrences(driverUserModel);
            }

            @Override
            public void onFailure(Call<DriverUserModel> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("LandingPresenter----- uploadProfileName onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });

    }


}
