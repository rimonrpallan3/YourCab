package com.voyager.sayaradriver.registerpage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.registerpage.model.IUserValidate;
import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.registerpage.view.IRegisterView;
import com.voyager.sayaradriver.test.MainClass;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 8/29/2017.
 */

public class RegisterPresenter implements IRegisterFetcher{

    IRegisterView iRegisterView;
    IUserValidate user;
    String FName;
    String LName;
    String email;
    String phno;
    String country;
    String city;
    String CPR;
    DriverDetails driverDetail;


    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    Context context;


    public RegisterPresenter(IRegisterView iRegisterView, Context context, SharedPreferences sharedPrefs, SharedPreferences.Editor editor) {
        this.iRegisterView =iRegisterView;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
        this.context = context;
    }

    @Override
    public void doRegister(String FName, String LName,String email, String phno,String country, String city, String CPR) {
        System.out.println("FName : " + FName +
                " LName : " + LName +
                " email Address : " + email +
                " phno : " + phno +
                " city : " + city +
                " CPR : " + CPR);
        this.FName = FName;
        this.LName = LName;
        this.email = email;
        this.phno = phno;
        this.country = country;
        this.city = city;
        this.CPR = CPR;
        initUser();
        Boolean isLoginSuccess =true;
        final int code = user.validateUserDetails(FName, LName, email, phno,country, city, CPR);
        if (code != 0) {
            isLoginSuccess = false;
        } else {
            sendRegisteredDataAndValidateResponse();
        }
        Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);
    }


    public void sendRegisteredDataAndValidateResponse(){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<DriverDetails> call = webServices.registerUser(FName,LName,email,phno,country,city,CPR);
        call.enqueue(new Callback<DriverDetails>() {
            @Override
            public void onResponse(Call<DriverDetails> call, Response<DriverDetails> response) {
                DriverDetails driverDetails  = (DriverDetails) response.body();
                System.out.println("-------sendRegisteredDataAndValidateResponse  FName : " + FName +
                        " LName : " + LName +
                        " email Address : " + email +
                        " phno : " + phno +
                        " city : " + city +
                        " CPR : " + CPR);
                System.out.println("----- sendRegisteredDataAndValidateResponse isError: "+driverDetails.isError +" driver_id: "+driverDetails.driver_id+" created_at: "+driverDetails.created_at);
                System.out.println("--------- sendRegisteredDataAndValidateResponse isError: "+driverDetails.isError +" Error message: "+driverDetails.error_msg);
                    final int code =user.validateRegisterResponseError(driverDetails.error_msg);
                    Boolean isLoginSuccess =true;
                    if (code != 0) {
                        isLoginSuccess = false;
                        Toast.makeText((Context) iRegisterView, driverDetails.getError_msg(), Toast.LENGTH_SHORT).show();
                        System.out.println("-----sendRegisteredDataAndValidateResponse  data unSuccess ");
                    } else {
                        Toast.makeText((Context) iRegisterView, "Register Successful", Toast.LENGTH_SHORT).show();
                        addUserGsonInSharedPrefrences();

                        System.out.println("----- sendRegisteredDataAndValidateResponse data Successful ");
                    }
                    Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iRegisterView.onRegistered(result, code, driverDetails.driver_id);
            }

            @Override
            public void onFailure(Call<DriverDetails> call, Throwable t) {
                Boolean isLoginSuccess =false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iRegisterView.onRegistered(result, code, "nill");
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void addUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        //DriverUserModel user1 = gson.fromJson(jsonString,DriverUserModel.class);
        if(jsonString!=null) {
            editor.putString("json", jsonString);
            editor.commit();
        }

    }

    private void initUser(){
        user = new DriverDetails(FName,LName,email,phno,city,CPR);
        driverDetail = new DriverDetails(FName,LName,email,phno,city,CPR);
    }
}
