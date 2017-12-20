package com.voyager.sayaradriver.signinpage.model;

/**
 * Created by estel on 2015/10/12.
 */
public interface IUser {
	String getUserName();

	String getPasswd();

	int checkUserValidity(String name, String passwd);
	int validateLoginResponseError(Boolean isError);
}
