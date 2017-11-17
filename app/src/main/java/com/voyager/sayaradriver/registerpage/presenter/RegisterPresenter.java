package com.voyager.sayaradriver.registerpage.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.registerpage.model.IUserValidate;
import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.registerpage.model.ResponseError2;
import com.voyager.sayaradriver.registerpage.view.IRegisterView;
import com.voyager.sayaradriver.test.MainClass;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.io.IOException;
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
    String phno;
    String city;
    String CPR;
    DriverDetails userDetails;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView =iRegisterView;
        initUser();
    }

    @Override
    public void doRegister(String FName, String LName, String phno, String city, String CPR) {
        System.out.println("FName : "+FName+" LName : "+LName+" phno : "+phno+" city : "+city+" CPR : "+CPR);
        Boolean isLoginSuccess = true;
        final int code = user.validateUserDetails(FName,LName,phno,city,CPR);
        if (code!=0) {
            isLoginSuccess = false;
        }else {
            this.FName = FName;
            this.LName = LName;
            this.phno = phno;
            this.city = city;
            this.CPR = CPR;
            initUser();
            Retrofit retrofit = new ApiClient().getRetrofitClient();
            WebServices webServices = retrofit.create(WebServices.class);
            Call<ResponseError2> call = webServices.registerUser2(FName,LName,phno,city,CPR);
            call.enqueue(new Callback<ResponseError2>() {
                @Override
                public void onResponse(Call<ResponseError2> call, Response<ResponseError2> response) {
                    ResponseError2 errorModel  = (ResponseError2) response.body();

                    System.out.println("errorModel: "+response.body());

                    if(errorModel!=null){
                        Toast.makeText((Context) iRegisterView, "error"+errorModel.error+"driver_id"+errorModel.driver_id+"created_at"+errorModel.created_at, Toast.LENGTH_SHORT).show();
                        System.out.println("error:   "+errorModel.error+" driver_id:   "+errorModel.driver_id+" created_at:  "+errorModel.created_at);
                    }
                }

                @Override
                public void onFailure(Call<ResponseError2> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        final Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);
    }

    private void initUser(){
        user = new DriverDetails(FName,LName,phno,city,CPR);
    }
}
