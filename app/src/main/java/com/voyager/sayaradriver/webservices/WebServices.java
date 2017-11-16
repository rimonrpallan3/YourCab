package com.voyager.sayaradriver.webservices;

import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.test.MainClass;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {
    @GET("list/")
    Call<MainClass> doGetUserList(@Query("page") String page);

    @POST("test/register/")
    public Call<DriverDetails> registerUser(@Body DriverDetails userDetails);
   /* @Multipart
    @POST("DriverRegisterServlet")
    Call<UserModel> uploadFile(@Part MultipartBody.Part licenseFile, @Part MultipartBody.Part rcFile, @Part MultipartBody.Part profileFile, @Part("name") RequestBody name);
    @Multipart
    @POST("DriverProfileUpdateServlet")
    Call<UserModel> driverProfileUpdate(@Part MultipartBody.Part licenseFile, @Part MultipartBody.Part rcFile, @Part MultipartBody.Part profileFile, @Part("name") RequestBody name);

    @POST("AndroidLoginServlet")
//    @FormUrlEncoded
    public Call<UserModel> loginUser(@Body UserModel userModel);

    @POST("AndroidRegisterServlet")
    public Call<UserModel> registerUser(@Body UserModel userModel);

    @POST("AndroidUpdateProfileServlet")
    public Call<UserModel> updateProfile(@Body UserModel userModel);

    @POST("TripServlet")
    public Call<TripDetailsModel> userRequestTrip(@Body TripDetailsModel tripModel);

    @GET("HistoryServlet")
    public Call<List<TripDetailsModel>> getUserHistory(@Query("user_name") String userName);

    @GET("DriverHistoryServlet")
    public Call<List<TripDetailsModel>> getDriverHistory(@Query("user_name") String userName);

    @GET("AndroidGetProfileServlet")
    public Call<UserModel> getUserInfo(@Query("user_name") String userName);

    @GET("DriverAcceptRejectServlet")
    public Call<UserModel> acceptRejectTrip(@Query("id") String id, @Query("status") String status);

    @POST("FCMUpdateServlet")
    public Call<UserModel> updateFCMId(@Body UserModel userModel);


    @GET("json")
    public Call<GetPaths> getPaths(@Query("origin") String origin, @Query("destination") String dest, @Query("sensor") String sensor);

    @POST("RechargeServlet")
    Call<RechargeModel> userWalletRecharge(@Body RechargeModel rechargeModel);

    @POST("LocationUpdateServlet")
    Call<UserModel> driverLocationUpdate(@Body UserModel userModel);

    @GET("GetPriceInfoServlet")
    Call<AutoChargeModel> updateFee();

    @GET("DriverProfileServlet")
    Call<UserModel> getDriverInfo(@Query("user_name") String userName);

    @GET("StartStopTripServlet")
    Call<UserModel> startStopTrip(@Query("id") String id, @Query("status") String status, @Query("payment_mode") String paymentMode);

    @GET("UserFeedbackServlet")
    Call<UserModel> userFeedBack(@Query("id") String id, @Query("rating") String userRating);
    @GET("DriverLocationServlet")
    Call<UserModel> getDriverLocation(@Query("user_name") String username);*/
}
