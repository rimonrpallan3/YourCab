package com.voyager.sayaradriver.webservices;

import android.support.annotation.Nullable;

import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.test.MainClass;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {
    @GET("driver/getDriver/")
    Call<MainClass> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("driver/register/")
    public Call<DriverDetails> registerUser(@Nullable @Field("driver_first_name") String FName,
                                            @Nullable @Field("driver_last_name") String LName,
                                            @Nullable @Field("driver_email") String email,
                                            @Nullable @Field("driver_phone") String phno,
                                            @Nullable @Field("driver_city") String city,
                                            @Nullable @Field("cpr") String CPR);

    @FormUrlEncoded
    @POST("register.php")
    Call<DriverDetails> getRegisterResult(@FieldMap HashMap<String, String> authData);
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
