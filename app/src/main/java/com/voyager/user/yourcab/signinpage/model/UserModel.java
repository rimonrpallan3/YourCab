package com.voyager.user.yourcab.signinpage.model;

/**
 * Created by kaede on 2015/5/18.
 */
public class UserModel implements IUser {
	String emailPhno;
	String passwd;

	public UserModel(String emailPhno, String passwd) {
		this.emailPhno = emailPhno;
		this.passwd = passwd;
	}

	@Override
	public String getEmailPhno() {
		return emailPhno;
	}


	@Override
	public String getPasswd() {
		return passwd;
	}

	@Override
	public int checkUserValidity(String name, String passwd){
		if (name==null||passwd==null||!name.equals(getEmailPhno())||!passwd.equals(getPasswd())){
			return -1;
		}
		return 0;
	}
}
