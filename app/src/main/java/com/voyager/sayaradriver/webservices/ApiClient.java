package com.voyager.sayaradriver.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static Retrofit pathRetrofit = null;
    private static OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(300, TimeUnit.SECONDS).
            readTimeout(300, TimeUnit.SECONDS).
            build();


    /*HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
       logging.setlevel(HttpLoggingInterceptor.Level.BODY)

         OkHttpClient.Builder httpClient = new OkHttpClient.Builder();*/
        // add your other interceptors …

        // add logging as last interceptor
        //httpClient.addInterceptor(logging);

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            /*http://10.1.1.21/sayara/*/
            retrofit = new Retrofit.Builder().baseUrl("http://10.1.1.18/sayara/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }


    public static Retrofit getRetrofitClientPath() {
        if (pathRetrofit == null) {
            pathRetrofit = new Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/directions/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return pathRetrofit;
    }
}
