package com.voyager.user.yourcab.registerpage.model;

/**
 * Created by User on 8/30/2017.
 */

public class UserDetails  implements  IUserValidate{
    String FName;
    String LName;
    String phno;
    String city;
    String CPR;

    public UserDetails(String FName, String LName, String phno, String city, String CPR) {
        this.FName = FName;
        this.LName = LName;
        this.phno = phno;
        this.city = city;
        this.CPR = CPR;
    }

    @Override
    public String getFirstName() {
        return FName;
    }

    @Override
    public String getLastName() {
        return LName;
    }

    @Override
    public String getPhoneNumber() {
        return phno;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCPR() {
        return CPR;
    }


    @Override
    public int validateUserDetails(String FName, String LName, String phno, String city, String CPR) {
        if (FName==null||LName==null||phno==null||city==null||LName==null||CPR==null){
            return -1;
        }
        return 0;
    }
}
