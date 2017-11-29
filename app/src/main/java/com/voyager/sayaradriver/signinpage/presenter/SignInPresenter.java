package com.voyager.sayaradriver.signinpage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.signinpage.model.IUser;
import com.voyager.sayaradriver.signinpage.model.UserModel;
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
    IUser user;
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
        final int code = user.checkUserValidity(name,passwd);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        iSignInView.onLoginResult(result, code);
        validateLoginDataBaseApi();

    }

    public void validateLoginDataBaseApi(){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<UserModel> call = webServices.loginUser(name,passwd);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel  = (UserModel) response.body();
                System.out.println("-------validateLoginDataBaseApi  userName : " + name +
                        " Password : " + passwd+
                        " FName : " + userModel.getFName() +
                        " LName : " + userModel.getLName()+
                        " email Address : " + userModel.getEmail() +
                        " phno : " + userModel.getPhno() +
                        " city : " + userModel.getCity() +
                        " CPR : " + userModel.getCPR());
                System.out.println("----- validateLoginDataBaseApi isError: "+userModel.isError +" driver_id: "+userModel.driver_id);
                System.out.println("--------- validateLoginDataBaseApi isError: "+userModel.isError +" Error message: "+userModel.error_msg);
                final int code =user.validateLoginResponseError(userModel.error_msg);
                Boolean isLoginSuccess =true;
                if (code != 0) {
                    isLoginSuccess = false;
                    Toast.makeText((Context) iSignInView, userModel.getError_msg(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    Toast.makeText((Context) iSignInView, "Register Successful", Toast.LENGTH_SHORT).show();
                    addUserGsonInSharedPrefrences();
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iSignInView.onLoginResponse(result, code);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Boolean isLoginSuccess =false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iSignInView.onLoginResult(result, code);
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        //UserModel user1 = gson.fromJson(jsonString,UserModel.class);
        if(jsonString!=null) {
            editor.putString("DriverDetails", jsonString);
            editor.commit();
        }

    }


    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iSignInView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser(){
        user = new UserModel(name,passwd);
    }
}
