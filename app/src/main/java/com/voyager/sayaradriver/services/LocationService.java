package com.voyager.sayaradriver.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationService extends Service implements LocationListener {
    LocationManager locationManager;
    Retrofit retrofit;
    WebServices webServices;

    public LocationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("----------------- LocationService");
        retrofit = ApiClient.getRetrofitClient();
        webServices = retrofit.create(WebServices.class);
        //username = getSharedPreferences(AppConstants.SHARED_PREF, MODE_PRIVATE).getString(AppConstants.USER_NAME, "");
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 12000, 3, LocationService.this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 12000, 3, LocationService.this);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            DriverUserModel driverUserModel = new DriverUserModel();
            driverUserModel.setDriverId(95);
            driverUserModel.setDriverLat(location.getLatitude());
            driverUserModel.setDriverLog(location.getLongitude());
            Toast.makeText(getApplicationContext(), location.getLatitude() + ","  + location.getLongitude(), Toast.LENGTH_SHORT).show();
            Log.e("Driver long: ", location.getLongitude() + "");
            System.out.println("Driver long:"+location.getLongitude()+",lat : "+location.getLatitude());
            Call<DriverUserModel> call = webServices.driverProfileStatus(driverUserModel.getDriverId(),driverUserModel.getDriverLat(),driverUserModel.getDriverLog());
            call.enqueue(new Callback<DriverUserModel>() {
                @Override
                public void onResponse(Call<DriverUserModel> call, Response<DriverUserModel> response) {
                    DriverUserModel model = response.body();
                }

                @Override
                public void onFailure(Call<DriverUserModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(LocationService.this);
        super.onDestroy();
    }
}
