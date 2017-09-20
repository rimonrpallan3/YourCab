package com.voyager.sayaradriver.registerpage.model;

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
    public int validateUserDetails(String FName, String LName, String phno, String city, String CPR) {
        if (FName.trim().length()==0||LName.trim().length()==0||phno.trim().length()==0||city.trim().length()==0||LName.trim().length()==0||CPR.trim().length()==0){
            {
                    return -1;
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
        return 0;
    }
}
