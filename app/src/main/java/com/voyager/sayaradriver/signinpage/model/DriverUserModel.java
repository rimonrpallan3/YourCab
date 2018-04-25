package com.voyager.sayaradriver.signinpage.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaede on 2015/5/18.
 */
public class DriverUserModel implements IUser,Parcelable {

	@SerializedName("username")
	public String userName="";
	@SerializedName("password")
	public String passwd="";
	@SerializedName("driver_id")
	public int driverId=0;
	@SerializedName("driver_first_name")
	public String FName="";
	@SerializedName("driver_last_name")
	public String LName="";
	@SerializedName("driver_email")
	public String email="";
	@SerializedName("driver_phone")
	public String phno="";
	@SerializedName("driver_city")
	public String city="";
	@SerializedName("driver_cpr")
	public String CPR="";
	@SerializedName("driver_country")
	public String country="";
	@SerializedName("error")
	public boolean isError   = Boolean.parseBoolean("");
	@SerializedName("error_msg")
	public String error_msg="";
	@SerializedName("driver_online")
	public String driverStatus="";
	@SerializedName("driver_online_change")
	public String adiminDriverStatus="";
	@SerializedName("driver_latitude")
	public double driverLat;
	@SerializedName("driver_longitude")
	public double driverLog;




	public DriverUserModel() {
	}


	protected DriverUserModel(Parcel in) {
		userName = in.readString();
		FName = in.readString();
		LName = in.readString();
		passwd = in.readString();
		CPR = in.readString();
		email = in.readString();
		phno = in.readString();
		city = in.readString();
		country = in.readString();
		error_msg =in.readString();
		isError = in.readByte() != 0;
		driverId = in.readInt();
		driverStatus = in.readString();
		adiminDriverStatus = in.readString();
		driverLat = in.readDouble();
		driverLog = in.readDouble();
	}

	@Override
	public int describeContents() {
		System.out.println("Describe the content DriverUserModel");
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		System.out.println("writeToParcel to DriverUserModel");
		dest.writeString(userName);
		dest.writeString(FName);
		dest.writeString(LName);
		dest.writeString(passwd);
		dest.writeString(CPR);
		dest.writeString(email);
		dest.writeString(phno);
		dest.writeString(city);
		dest.writeString(country);
		dest.writeString(error_msg);
		dest.writeByte((byte) (isError ? 1 : 0));
		dest.writeInt(driverId);
		dest.writeString(driverStatus);
		dest.writeString(adiminDriverStatus);
		dest.writeDouble(driverLat);
		dest.writeDouble(driverLog);
	}


	public static final Parcelable.Creator<DriverUserModel> CREATOR = new Parcelable.Creator<DriverUserModel>() {
		@Override
		public DriverUserModel createFromParcel(Parcel in) {
			return new DriverUserModel(in);
		}

		@Override
		public DriverUserModel[] newArray(int size) {
			return new DriverUserModel[size];
		}
	};

	public static Creator<DriverUserModel> getCREATOR() {
		return CREATOR;
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

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	public double getDriverLat() {
		return driverLat;
	}

	public void setDriverLat(double driverLat) {
		this.driverLat = driverLat;
	}

	public double getDriverLog() {
		return driverLog;
	}

	public void setDriverLog(double driverLog) {
		this.driverLog = driverLog;
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
