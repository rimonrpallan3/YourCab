package com.voyager.sayaradriver.test;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 08-Nov-17.
 */

public class MainClass {
    @SerializedName("output")
    public List<SubClass> output = new ArrayList();

    public static class SubClass {

        @SerializedName("driverId")
        public String id;
        @SerializedName("driver_first_name")
        public String fname;
        @SerializedName("driver_last_name")
        public String lname;
        @SerializedName("driver_email")
        public String email;
        @SerializedName("driver_phone")
        public String phone;
        @SerializedName("driver_city")
        public String city;
        @SerializedName("driver_country")
        public String country;
        @SerializedName("cpr")
        public String cpr;
        @SerializedName("driver_status")
        public boolean status;
        @SerializedName("doj")
        public String date;
    }

}
