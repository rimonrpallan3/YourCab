package com.voyager.user.yourcab.signinpage.model;

/**
 * Created by estel on 2015/10/12.
 */
public interface IUser {
	String getEmailPhno();

	String getPasswd();

	int checkUserValidity(String name, String passwd);
}
