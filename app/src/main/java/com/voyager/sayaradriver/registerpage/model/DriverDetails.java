package com.voyager.sayaradriver.registerpage.model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("cpr")
    String CPR;
    @SerializedName("success")
    public boolean isError   = Boolean.parseBoolean("");
    @SerializedName("driver_id")
    public String driver_id="";
    @SerializedName("created_at")
    public String created_at="";
    @SerializedName("error_msg")
    public String error_msg="";

    public DriverDetails(String FName, String LName,String email, String phno, String city, String CPR) {
        this.FName = FName;
        this.LName = LName;
        this.email = email;
        this.phno = phno;
        this.city = city;
        this.CPR = CPR;
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
    public int validateUserDetails(String FName, String LName,String email, String phno, String city, String CPR) {
        if (FName.trim().length()==0||LName.trim().length()==0||email.trim().length()==0||phno.trim().length()==0||city.trim().length()==0||CPR.trim().length()==0){
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
            for (int i = 0; i < phno.trim().length(); i++) {
                char charAt2 = phno.trim().charAt(i);
                if (!Character.isDigit(charAt2)) {
                    return -4;
                }
            }
            for (int i = 0; i < city.trim().length(); i++) {
                char charAt2 = city.trim().charAt(i);
                if (!Character.isLetter(charAt2)) {
                    return -5;
                }
            }
            for (int i = 0; i < CPR.trim().length(); i++) {
                char charAt2 = CPR.trim().charAt(i);
                if (!Character.isDigit(charAt2)) {
                    return -6;
                }
            }
        }
        return 0;// Validation is successful
    }

    @Override
    public int validateRegisterResponseError(String errorMsg) {
        if(errorMsg.trim().length()==0){
            return 0;
        }
        return -7;
    }
}
