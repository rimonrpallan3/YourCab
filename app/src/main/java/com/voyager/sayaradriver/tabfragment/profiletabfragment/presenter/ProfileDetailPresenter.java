package com.voyager.sayaradriver.tabfragment.profiletabfragment.presenter;

import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.view.IProfileView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 09-Feb-18.
 */

public class ProfileDetailPresenter implements IProfilePresenter{
    IProfileView iProfileView;
    DriverUserModel driverUserModel;

    public ProfileDetailPresenter(IProfileView iProfileView, DriverUserModel driverUserModel) {
        this.iProfileView = iProfileView;
        this.driverUserModel = driverUserModel;
    }


    @Override
    public void loadData() {
        System.out.println("-------EarningPresenter");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<List<ProfileModel>> call = webServices.getProfileDetail(driverUserModel.driverId);
        call.enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                List<ProfileModel> profileModel  = response.body();
                System.out.println("-------ProfileDetailPresenter loadData driverName : " + profileModel.get(0).getDriverName() +
                        " driverPhone : " + profileModel.get(0).getDriverPhone() +
                        " driverCity : " + profileModel.get(0).getDriverCity() +
                        " driverCountry : " + profileModel.get(0).getDriverCountry() +
                        " driverCpr : " + profileModel.get(0).getDriverCpr() +
                        " driverPhoto : " + profileModel.get(0).getDriverPhoto() +
                        " driverRating : " + profileModel.get(0).getDriverRating() +
                        " driverPhoto : " + profileModel.get(0).getDriverPhoto() +
                        " carName : " + profileModel.get(0).getCarName() +
                        " driverId : " + profileModel.get(0).getDriverId());
                iProfileView.loadData(profileModel);

            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {

                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    
    
    
    
    
}
