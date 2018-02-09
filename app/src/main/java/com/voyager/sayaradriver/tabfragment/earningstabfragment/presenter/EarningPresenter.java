package com.voyager.sayaradriver.tabfragment.earningstabfragment.presenter;

import android.content.Context;
import android.widget.Toast;

import com.voyager.sayaradriver.tabfragment.earningstabfragment.Model.EarningModel;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.view.IEarningView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 09-Feb-18.
 */

public class EarningPresenter implements IEarningPresenter{

    IEarningView iEarningView;

    public EarningPresenter(IEarningView iEarningView) {
        this.iEarningView = iEarningView;
    }


    @Override
    public void loadData() {
        System.out.println("-------EarningPresenter");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<EarningModel> call = webServices.getEarnings(89);
        call.enqueue(new Callback<EarningModel>() {
            @Override
            public void onResponse(Call<EarningModel> call, Response<EarningModel> response) {
                EarningModel earningModel  = response.body();
                System.out.println("-------EarningPresenter loadData trips : " + earningModel.getTrips() +
                        " totalEarning : " + earningModel.getTotalEarning() +
                        " balanceEarning : " + earningModel.getBalanceEarning());
                iEarningView.loadData(earningModel.getTrips(),earningModel.getTotalEarning(),earningModel.getBalanceEarning());
              
            }

            @Override
            public void onFailure(Call<EarningModel> call, Throwable t) {
               
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
