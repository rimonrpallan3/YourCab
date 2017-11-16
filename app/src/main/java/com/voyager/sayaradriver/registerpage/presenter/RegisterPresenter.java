package com.voyager.sayaradriver.registerpage.presenter;

import android.content.Context;
import android.widget.Toast;

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
            Call<DriverDetails> call = webServices.registerUser(userDetails);
            call.enqueue(new Callback<DriverDetails>() {
                @Override
                public void onResponse(Call<DriverDetails> call, Response<DriverDetails> response) {
                    String res = response.body().getResponse();
                    Toast.makeText((Context) iRegisterView, "registerUser: "+res, Toast.LENGTH_SHORT).show();
                    if (res.trim().equals("success")) {
                        Toast.makeText((Context) iRegisterView, "Successfully registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText((Context) iRegisterView, res, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DriverDetails> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText((Context) iRegisterView, "Network error", Toast.LENGTH_SHORT).show();
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
