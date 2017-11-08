package com.voyager.sayaradriver.loginsignuppage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.firstotppage.FirstOTPPage;
import com.voyager.sayaradriver.signinpage.SignInPage;
import com.voyager.sayaradriver.test.MainClass;
import com.voyager.sayaradriver.trackingid.TrackingID;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginSignUpPage extends AppCompatActivity {
    String id ="";
    String name ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sigup_page);

        System.out.println("Enter");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        final ProgressDialog progressDialog = new ProgressDialog(LoginSignUpPage.this);
        progressDialog.setTitle("wait");
        progressDialog.show();
        try {
            Call call  = webServices.doGetUserList("1");
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressDialog.dismiss();
                    MainClass model  = (MainClass) response.body();

                    System.out.println("model"+response.body());

                    if(model!=null){
                        List<MainClass.SubClass> subList = model.output;

                        for (MainClass.SubClass subClass : subList) {
                            Toast.makeText(getApplicationContext(), "id : " + subClass.id + " name: " + subClass.fname + " lastName:  " + subClass.lname + " PHone: " + subClass.phone, Toast.LENGTH_SHORT).show();
                        }

                    }

                /*    UserList userList = response.body();
                    Integer text = userList.page;
                    Integer total = userList.total;
                    Integer totalPages = userList.totalPages;
                    List datumList = userList.data;
                    Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                    for (UserList.Datum datum : datumList) {
                        Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                    }*/


                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressDialog.dismiss();
                    call.cancel();
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Full crash");
        }

    }

    public void btnSignIn(View v){
        Intent intent = new Intent(this, SignInPage.class);
        startActivity(intent);
    }

    public  void btnSignUp(View v){
        Intent intent = new Intent(this, FirstOTPPage.class);
        startActivity(intent);
    }
    public  void btnTrack(View v){
        Intent intent = new Intent(this, TrackingID.class);
        startActivity(intent);
    }
}
