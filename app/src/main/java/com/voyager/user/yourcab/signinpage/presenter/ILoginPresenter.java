package com.voyager.user.yourcab.signinpage.presenter;

/**
 * Created by kaede on 2015/10/12.
 */
public interface ILoginPresenter {
	void clear();
	void doLogin(String emailPhno, String passwd);
	void setProgressBarVisiblity(int visiblity);
}
