package com.voyager.sayaradriver.webservices;

import android.support.annotation.Nullable;

import com.voyager.sayaradriver.DocumentPage.model.DocModel;
import com.voyager.sayaradriver.registerpage.model.DriverDetails;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.Model.EarningModel;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.TripDetails;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.GetPaths;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.LogOut;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.model.RatingModel;
import com.voyager.sayaradriver.test.MainClass;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
                                            @Nullable @Field("driver_country") String country,
                                            @Nullable @Field("driver_city") String city,
                                            @Nullable @Field("driver_cpr") String CPR);
    @FormUrlEncoded
    @POST("driver/login/")
    public Call<DriverUserModel> loginUser(@Nullable @Field("username") String name,
                                           @Nullable @Field("password") String passwd,
                                           @Nullable @Field("token") String fireBaseToken);

    @FormUrlEncoded
    @POST("register.php")
    Call<DriverDetails> getRegisterResult(@FieldMap HashMap<String, String> authData);

    @Multipart
    @POST("driver/documents/")
    public Call<DocModel> uploadFile(@Part MultipartBody.Part  driving_license, @Part("driverId") RequestBody driverId,@Part("document_type") RequestBody docType);

    @FormUrlEncoded
    @POST("driver/updateStatus/")
    Call<DriverUserModel> driverProfileStatus(@Nullable @Field("driver_id") int driverID,
                                              @Nullable @Field("driver_online") String status);

    @FormUrlEncoded
    @POST("driver/rejectTrip/")
    Call<TripDetails> driverRejectTrip (@Nullable @Field("driver_id") int driverID,
                                        @Nullable @Field("trip_id") int status);
    @FormUrlEncoded
    @POST("driver/acceptTrip/")
    Call<TripDetails> driverAcceptTrip (@Nullable @Field("driver_id") int driverID,
                                        @Nullable @Field("trip_id") int status);

    @FormUrlEncoded
    @POST("driver/updateLocation/")
    Call<DriverUserModel> driverProfileStatus(@Nullable @Field("driver_id") int driverID,
                                              @Nullable @Field("driver_latitude") double driverLatitude,
                                              @Nullable @Field("driver_longitude") double driverLongitude);

    @POST("driver/updateLocation/")
    Call<DriverUserModel> driverLocationUpdate(@Body DriverUserModel driverUserModel);

    @FormUrlEncoded
    @POST("driver/myEarning/")
    Call<EarningModel> getEarnings(@Nullable @Field("driver_id") int driverID);

    @FormUrlEncoded
    @POST("driver/myRating/")
    Call<RatingModel> getRatings(@Nullable @Field("driver_id") int driverID);

    @FormUrlEncoded
    @POST("driver/myAccount/")
    Call<List<ProfileModel>> getProfileDetail(@Nullable @Field("driver_id") int driverID);

    @FormUrlEncoded
    @POST("driver/logout/")
    Call<LogOut> logedOut(@Nullable @Field("driver_id") int driverID, @Nullable @Field("logout") int logout);

    @GET("directions/json?")
    public Call<GetPaths> getPaths(@Query("origin") String origin, @Query("destination") String dest, @Query("sensor") Boolean sensor, @Query("key") String key);

    //Call<LogOut> logedOut(@Body LogOut logout);

    /*http://10.1.1.18/sayara/driver/myAccount/
            (08-Feb-18 3:38:15 PM) http://10.1.1.18/sayara/driver/myRating/
            (08-Feb-18 3:53:08 PM) http://10.1.1.18/sayara/driver/myEarning/*/


   /* @Multipart
    @POST("DriverRegisterServlet")
    Call<DriverUserModel> uploadFile(@Part MultipartBody.Part licenseFile, @Part MultipartBody.Part rcFile, @Part MultipartBody.Part profileFile, @Part("name") RequestBody name);
    @Multipart
    @POST("DriverProfileUpdateServlet")
    Call<DriverUserModel> driverProfileUpdate(@Part MultipartBody.Part licenseFile, @Part MultipartBody.Part rcFile, @Part MultipartBody.Part profileFile, @Part("name") RequestBody name);

    @POST("AndroidLoginServlet")
//    @FormUrlEncoded
    public Call<DriverUserModel> loginUser(@Body DriverUserModel userModel);

    @POST("AndroidRegisterServlet")
    public Call<DriverUserModel> registerUser(@Body DriverUserModel userModel);

    @POST("AndroidUpdateProfileServlet")
    public Call<DriverUserModel> updateProfile(@Body DriverUserModel userModel);

    @POST("TripServlet")
    public Call<TripDetailsModel> userRequestTrip(@Body TripDetailsModel tripModel);

    @GET("HistoryServlet")
    public Call<List<TripDetailsModel>> getUserHistory(@Query("user_name") String userName);

    @GET("DriverHistoryServlet")
    public Call<List<TripDetailsModel>> getDriverHistory(@Query("user_name") String userName);

    @GET("AndroidGetProfileServlet")
    public Call<DriverUserModel> getUserInfo(@Query("user_name") String userName);

    @GET("DriverAcceptRejectServlet")
    public Call<DriverUserModel> acceptRejectTrip(@Query("id") String id, @Query("status") String status);

    @POST("FCMUpdateServlet")
    public Call<DriverUserModel> updateFCMId(@Body DriverUserModel userModel);


    @GET("json")
    public Call<GetPaths> getPaths(@Query("origin") String origin, @Query("destination") String dest, @Query("sensor") String sensor);

    @POST("RechargeServlet")
    Call<RechargeModel> userWalletRecharge(@Body RechargeModel rechargeModel);

    @POST("LocationUpdateServlet")
    Call<DriverUserModel> driverLocationUpdate(@Body DriverUserModel userModel);

    @GET("GetPriceInfoServlet")
    Call<AutoChargeModel> updateFee();

    @GET("DriverProfileServlet")
    Call<DriverUserModel> getDriverInfo(@Query("user_name") String userName);

    @GET("StartStopTripServlet")
    Call<DriverUserModel> startStopTrip(@Query("id") String id, @Query("status") String status, @Query("payment_mode") String paymentMode);

    @GET("UserFeedbackServlet")
    Call<DriverUserModel> userFeedBack(@Query("id") String id, @Query("rating") String userRating);
    @GET("DriverLocationServlet")
    Call<DriverUserModel> getDriverLocation(@Query("user_name") String username);*/
}
