package com.voyager.sayaradriver.landingpage.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
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
    String online = "";
    String offline = "";


    public LandingPresenter(ILandingView iLandingView, Context context) {
        this.iLandingView = iLandingView;
        this.context = context;
    }

    @Override
    public void checkOfflineOnline(Boolean isChecked) {
        online = context.getString(R.string.driver_online);
        offline = context.getString(R.string.driver_offline);
        System.out.println("driverSwitch Clicked"+"yehhh!!");
        if (isChecked){
            System.out.println("driverSwitch Clicked :"+" true!!");
            iLandingView.getOfflineOnlineState(online);
        }
        else{
            System.out.println("driverSwitch Clicked :"+" false!!");
            iLandingView.getOfflineOnlineState(offline);
        }
    }


    public void uploadProfileName() {



        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

       /* Call<UserDetails> call = webServices.driverProfileStatus(name, userID);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call,
                                   Response<UserDetails> response) {

                UserDetails user =response.body();
                System.out.println("----- uploadProfileName isError: "+user.isError +" user_error: "+user.error_msg );

                final int code =user.checkUpdateNameApi(user.isError);
                Boolean isLoginSuccess =true;
                if (code != 0) {
                    isLoginSuccess = false;
                    Toast.makeText(context, user.getError_msg(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----uploadProfileName  data unSuccess ");
                } else {
                    user.setFName(name);
                    Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT).show();
                    addUserGsonInSharedPrefrences();
                    System.out.println("----- uploadProfileName data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- uploadProfileName second Data Please see, code = " + code + ", result: " + result);
                iUpdateProfile.nameUploadOnSuccess(result, code, name, userID);
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("uploadProfileName error:", t.getMessage());
            }
        });
*/

    }

}
