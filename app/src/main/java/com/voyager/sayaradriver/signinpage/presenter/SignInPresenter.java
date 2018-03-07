package com.voyager.sayaradriver.signinpage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.signinpage.view.ISignInView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 8/29/2017.
 */

public class SignInPresenter implements ILoginPresenter {

    ISignInView iSignInView;
    DriverUserModel driverUserModel;
    Handler handler;
    String name;
    String passwd;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    public SignInPresenter(ISignInView iSignInView,SharedPreferences sharedPrefs, SharedPreferences.Editor editor) {
        this.iSignInView = iSignInView;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
        handler = new Handler(Looper.getMainLooper());
        initUser();
    }

    @Override
    public void clear() {
        iSignInView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
        initUser();
        Boolean isLoginSuccess = true;
        final int code = driverUserModel.checkUserValidity(name,passwd);
        if (code!=0) {
            isLoginSuccess = false;
        } else{
            validateLoginDataBaseApi();
        }
        final Boolean result = isLoginSuccess;
        iSignInView.onLoginResult(result, code);


    }

    public void validateLoginDataBaseApi(){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<DriverUserModel> call = webServices.loginUser(name,passwd);
        call.enqueue(new Callback<DriverUserModel>() {
            @Override
            public void onResponse(Call<DriverUserModel> call, Response<DriverUserModel> response) {
                driverUserModel = (DriverUserModel) response.body();
                System.out.println("-------validateLoginDataBaseApi  driverUserModelName : " + name +
                        " Password : " + passwd+
                        " FName : " + driverUserModel.getFName() +
                        " LName : " + driverUserModel.getLName()+
                        " email Address : " + driverUserModel.getEmail() +
                        " phno : " + driverUserModel.getPhno() +
                        " city : " + driverUserModel.getCity() +
                        " CPR : " + driverUserModel.getCPR());
                driverUserModel.setUserName(name);
                driverUserModel.setPasswd(passwd);
                System.out.println("----- validateLoginDataBaseApi isError: "+ driverUserModel.isError +" driverId: "+ driverUserModel.driverId);
                System.out.println("--------- validateLoginDataBaseApi isError: "+ driverUserModel.isError +" Error message: "+ driverUserModel.error_msg);
                final int code =driverUserModel.validateLoginResponseError(driverUserModel.isError);
                Boolean isLoginSuccess =true;
                if (code != 0) {
                    isLoginSuccess = false;
                    Toast.makeText((Context) iSignInView, driverUserModel.getError_msg(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    Toast.makeText((Context) iSignInView, "Login Successful", Toast.LENGTH_SHORT).show();
                    addUserGsonInSharedPrefrences(driverUserModel);
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iSignInView.onLoginResponse(result, code);
            }

            @Override
            public void onFailure(Call<DriverUserModel> call, Throwable t) {
                Boolean isLoginSuccess =false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iSignInView.onLoginResult(result, code);
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addUserGsonInSharedPrefrences(DriverUserModel driverUserModel){
        Gson gson = new Gson();
        String jsonString = gson.toJson(driverUserModel);
        //DriverUserModel driverUserModel1 = gson.fromJson(jsonString,DriverUserModel.class);
        System.out.println("-----------addUserGsonInSharedPrefrences DriverUserModel"+jsonString);
        if(jsonString!=null) {
            System.out.println("-----------addUserGsonInSharedPrefrences DriverUserModel"+jsonString);
            editor.putString("DriverUserModel", jsonString);
            editor.commit();
        }

    }


    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iSignInView.onSetProgressBarVisibility(visiblity);
    }

    @Override
    public void onLoginSucuess() {
        iSignInView.sendPParcelableObj(driverUserModel);
    }

    private void initUser(){
        driverUserModel = new DriverUserModel(name,passwd);
    }
}
