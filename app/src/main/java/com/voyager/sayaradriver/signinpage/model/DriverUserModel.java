package com.voyager.sayaradriver.signinpage.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaede on 2015/5/18.
 */
public class DriverUserModel implements IUser {
	@SerializedName("username")
	public String userName;
	@SerializedName("password")
	public String passwd;
	@SerializedName("driver_id")
	public int driver_id;
	@SerializedName("driver_first_name")
	public String FName;
	@SerializedName("driver_last_name")
	public String LName;
	@SerializedName("driver_email")
	public String email;
	@SerializedName("driver_phone")
	public String phno;
	@SerializedName("driver_city")
	public String city;
	@SerializedName("driver_cpr")
	public String CPR;
	@SerializedName("driver_country")
	public String country;
	@SerializedName("error")
	public boolean isError   = Boolean.parseBoolean("");
	@SerializedName("error_msg")
	public String error_msg="";
	@SerializedName("driver_online")
	public String driverStatus="";
	@SerializedName("driver_online_change")
	public String adiminDriverStatus="";

	public DriverUserModel() {
	}

	public String getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getAdiminDriverStatus() {
		return adiminDriverStatus;
	}

	public void setAdiminDriverStatus(String adiminDriverStatus) {
		this.adiminDriverStatus = adiminDriverStatus;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean error) {
		isError = error;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public DriverUserModel(String userName, String passwd) {
		this.userName = userName;
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}


	@Override
	public String getPasswd() {
		return passwd;
	}

	@Override
	public int checkUserValidity(String userName, String passwd){
		if (userName==null||passwd==null||!userName.equals(getUserName())||!passwd.equals(getPasswd())){
			return -1;
		}
		return 0;
	}

	@Override
	public int validateLoginResponseError(Boolean isError) {
		if(isError==true){
			//if there is no error message then it means that data response is correct.
			return -2;
		}
		return 0;
	}
}
