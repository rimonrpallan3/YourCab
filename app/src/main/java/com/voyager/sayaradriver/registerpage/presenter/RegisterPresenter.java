package com.voyager.sayaradriver.registerpage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.registerpage.model.IUserValidate;
import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.registerpage.view.IRegisterView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

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
    public void doRegister(String FName, String LName,String email, String phno, String city, String CPR) {
        System.out.println("FName : " + FName + " LName : " + LName +" email Address : " + email + " phno : " + phno + " city : " + city + " CPR : " + CPR);
        Boolean isLoginSuccess =true;
        final int code = user.validateUserDetails(FName, LName, email, phno, city, CPR);
        int resultCode = 0;
        if (code != 0) {
            isLoginSuccess = false;
        } else {
            this.FName = FName;
            this.LName = LName;
            this.email = email;
            this.phno = phno;
            this.city = city;
            this.CPR = CPR;
            initUser();
            sendRegisteredDataAndValidateResponse();
        }
        Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);
        iRegisterView.onRegistered(result, resultCode);
    }


    public void sendRegisteredDataAndValidateResponse(){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<DriverDetails> call = webServices.registerUser(FName,LName,email,phno,city,CPR);
        call.enqueue(new Callback<DriverDetails>() {
            @Override
            public void onResponse(Call<DriverDetails> call, Response<DriverDetails> response) {
                DriverDetails driverDetails  = (DriverDetails) response.body();
                System.out.println("isError: "+driverDetails.isError +" driver_id: "+driverDetails.driver_id+" created_at: "+driverDetails.created_at);
                    System.out.println("isError: "+driverDetails.isError +" Error message: "+driverDetails.error_msg);
                    final int code =user.validateRegisterResponseError(driverDetails.error_msg);
                    Boolean isLoginSuccess =true;
                    if (code != 0) {
                        isLoginSuccess = false;
                        Toast.makeText((Context) iRegisterView, driverDetails.getError_msg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText((Context) iRegisterView, "Register Successful", Toast.LENGTH_SHORT).show();
                        addUserGsonInSharedPrefrences();
                    }
                    Boolean result = isLoginSuccess;
                    iRegisterView.onRegistered(result, code);
            }

            @Override
            public void onFailure(Call<DriverDetails> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void addUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        //UserModel user1 = gson.fromJson(jsonString,UserModel.class);
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
