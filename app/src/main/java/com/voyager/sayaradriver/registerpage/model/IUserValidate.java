package com.voyager.sayaradriver.registerpage.model;

/**
 * Created by User on 8/30/2017.
 */

public interface IUserValidate {
    public int validateUserDetails(String FName, String LName,String email, String phno,String country, String city, String CPR);
    int validateRegisterResponseError(String errorMsg);
}
