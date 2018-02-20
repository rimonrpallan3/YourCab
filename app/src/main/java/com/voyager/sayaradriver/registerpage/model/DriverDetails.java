package com.voyager.sayaradriver.registerpage.model;

import com.google.gson.annotations.SerializedName;
import com.voyager.sayaradriver.test.MainClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/30/2017.
 */

public class DriverDetails implements  IUserValidate{
    @SerializedName("driver_first_name")
    String FName;
    @SerializedName("driver_last_name")
    String LName;
    @SerializedName("driver_email")
    String email;
    @SerializedName("driver_phone")
    String phno;
    @SerializedName("driver_city")
    String city;
    @SerializedName("driver_cpr")
    String CPR;
    @SerializedName("error")
    public boolean isError   = Boolean.parseBoolean("");
    @SerializedName("driverId")
    public String driver_id="";
    @SerializedName("created_at")
    public String created_at="";
    @SerializedName("error_msg")
    public String error_msg="";
    @SerializedName("errorList")
    double lat;
    double longi;




    public List<DriverDetails.ErrorClass> errorList = new ArrayList();

    public  DriverDetails(){

    }

    public DriverDetails(String FName, String LName,String email, String phno, String city, String CPR) {
        this.FName = FName;
        this.LName = LName;
        this.email = email;
        this.phno = phno;
        this.city = city;
        this.CPR = CPR;
    }

    public static class ErrorClass {

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
        @SerializedName("driver_cpr")
        public String cpr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        this.isError = error;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }


    @Override
    public int validateUserDetails(String FName,
                                   String LName,
                                   String email,
                                   String phno,
                                   String country,
                                   String city,
                                   String CPR) {
        if (FName.trim().length()==0||
                LName.trim().length()==0||
                email.trim().length()==0||
                phno.trim().length()==0||
                city.trim().length()==0||
                CPR.trim().length()==0){
            {
                    return -1;// if the field is null
                }
        }else {
            for (int i = 0; i < FName.trim().length(); i++) {
                char charAt2 = FName.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -2;
                }
            }
            for (int i = 0; i < LName.trim().length(); i++) {
                char charAt2 = LName.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -3;
                }
            }
            for (int i = 0; i < email.trim().length(); i++) {
                String charAt2 = email.trim().toString();
                if (!charAt2.contains("@")&&!charAt2.contains(".")) {
                    return -4;
                }
            }
            for (int i = 0; i < phno.trim().length(); i++) {
                String charAt2 = phno.trim().toString();
                if (charAt2==null) {
                    return -5;
                }
            }
            for (int i = 0; i < country.trim().length(); i++) {
                char charAt2 = country.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -6;
                }
            }
            for (int i = 0; i < city.trim().length(); i++) {
                char charAt2 = city.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -7;
                }
            }
            for (int i = 0; i < CPR.trim().length(); i++) {
                char charAt2 = CPR.trim().charAt(i);
                if (!Character.isDigit(charAt2)) {
                    return -8;
                }
            }
        }
        return 0;// Validation is successful
    }

    @Override
    public int validateRegisterResponseError(String errorMsg) {
        if(errorMsg!=null){
            //if there is no error message then it means that data response is correct.
            return -9;
        }
        return 0;
    }
}
