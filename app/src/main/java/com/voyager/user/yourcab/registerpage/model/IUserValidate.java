package com.voyager.user.yourcab.registerpage.model;

/**
 * Created by User on 8/30/2017.
 */

public interface IUserValidate {
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getCity();
    String getCPR();
    int validateUserDetails(String FName, String LName, String phno, String city, String CPR);
}
