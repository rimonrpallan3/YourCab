package com.voyager.sayaradriver.tabfragment.ratingstabfragment.presenter;

import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.model.RatingModel;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.view.IRatingView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 09-Feb-18.
 */

public class RatingPresenter implements IRatingPresenter{
    IRatingView iRatingView;
    DriverUserModel driverUserModel;

    public RatingPresenter(IRatingView iRatingView,DriverUserModel driverUserModel) {
        this.iRatingView = iRatingView;
        this.driverUserModel = driverUserModel;
    }


    @Override
    public void loadData() {
        System.out.println("-------RatingPresenter");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<RatingModel> call = webServices.getRatings(driverUserModel.getDriverId());
        call.enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                RatingModel ratingModel  = response.body();
                System.out.println("-------RatingPresenter loadData driverName : " + ratingModel.getDriverName() +
                        " driverCity : " + ratingModel.getDriverCity()+
                        " error : " + ratingModel.isError() +
                        " driverRating : " + ratingModel.getDriverRating() +
                        " driverPhoto : " + ratingModel.getDriverPhoto());
                iRatingView.loadData(ratingModel.getDriverName(),ratingModel.getDriverCity(),ratingModel.getDriverRating(),ratingModel.getDriverPhoto(),ratingModel.isError());

            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
