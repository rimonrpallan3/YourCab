package com.voyager.sayaradriver.registerpage.presenter;

import android.content.Context;
import android.widget.Toast;

import com.voyager.sayaradriver.registerpage.model.IUserValidate;
import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.registerpage.view.IRegisterView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.util.HashMap;
import java.util.Map;

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
    DriverDetails userDetails;
    Map<String,String> getRegisterParamenters = new HashMap<String ,String>();

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView =iRegisterView;
        initUser();
    }

    @Override
    public void doRegister(String FName, String LName,String email, String phno, String city, String CPR) {
        System.out.println("FName : "+FName+" LName : "+LName+" phno : "+phno+" city : "+city+" CPR : "+CPR);
        Boolean isLoginSuccess = true;
        final int code = user.validateUserDetails(FName,LName,email,phno,city,CPR);
        if (code!=0) {
            isLoginSuccess = false;
        }else {
            this.FName = FName;
            this.LName = LName;
            this.email = email;
            this.phno = phno;
            this.city = city;
            this.CPR = CPR;
            initUser();
            Retrofit retrofit = new ApiClient().getRetrofitClient();
            WebServices webServices = retrofit.create(WebServices.class);
            getRegisterParamenters.put("driver_first_name",FName);
            getRegisterParamenters.put("driver_last_name",LName);
            getRegisterParamenters.put("driver_last_name",email);
            getRegisterParamenters.put("driver_phone",phno);
            getRegisterParamenters.put("driver_city",city);
            getRegisterParamenters.put("cpr",CPR);

            //Call<RegisterResult> call = webServices.getRegisterResult((HashMap<String, String>) getRegisterParamenters);
            Call<DriverDetails> call = webServices.registerUser(FName,LName,email,phno,city,CPR);
            call.enqueue(new Callback<DriverDetails>() {
                @Override
                public void onResponse(Call<DriverDetails> call, Response<DriverDetails> response) {
                    DriverDetails errorModel  = (DriverDetails) response.body();

                    System.out.println("errorModel: "+response.body());

                    if(errorModel!=null){
                        Toast.makeText((Context) iRegisterView, "error"+errorModel.error+"driver_id"+errorModel.driver_id+"created_at"+errorModel.created_at, Toast.LENGTH_SHORT).show();
                        System.out.println("error:   "+errorModel.error+" driver_id:   "+errorModel.driver_id+" created_at:  "+errorModel.created_at);
                    }
                }

                @Override
                public void onFailure(Call<DriverDetails> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        final Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);
    }

    private void initUser(){
        user = new DriverDetails(FName,LName,email,phno,city,CPR);
    }
}
