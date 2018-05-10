package com.voyager.sayaradriver.tabfragment.hometabfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 15-Mar-18.
 */

public class FCMDetials implements Parcelable {

    /**
     * userCity : Kochi
     * fare : 20 BD
     * userCountry : India
     * payType : Cash
     * userPhoto : http://10.1.1.18/sayara/uploads/user/Shijo-Joseph227.jpg
     * distance : 25 KM
     * pickupAddress : Cherthala, Kerala
     * user_id : 1001
     * tripId : 100045
     * userName : Shijo Joseph
     * dropAddress : Alappuzha, Kerala
     */

    private String userCity;
    private String fare;
    private String userCountry;
    private String payType;
    private String userPhoto;
    private String distance;
    private String pickupAddress;
    private String user_id;
    private int tripId;
    private String userName;
    private String dropAddress;
    private String tripStatus;
    private String pickupLocation;
    private String drop_loc;

    public String getDrop_loc() {
        return drop_loc;
    }

    public void setDrop_loc(String drop_loc) {
        this.drop_loc = drop_loc;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userCity);
        dest.writeString(this.fare);
        dest.writeString(this.userCountry);
        dest.writeString(this.payType);
        dest.writeString(this.userPhoto);
        dest.writeString(this.distance);
        dest.writeString(this.pickupAddress);
        dest.writeString(this.user_id);
        dest.writeInt(this.tripId);
        dest.writeString(this.userName);
        dest.writeString(this.dropAddress);
        dest.writeString(this.tripStatus);
        dest.writeString(this.pickupLocation);
        dest.writeString(this.drop_loc);
    }

    public FCMDetials() {
    }

    protected FCMDetials(Parcel in) {
        this.userCity = in.readString();
        this.fare = in.readString();
        this.userCountry = in.readString();
        this.payType = in.readString();
        this.userPhoto = in.readString();
        this.distance = in.readString();
        this.pickupAddress = in.readString();
        this.user_id = in.readString();
        this.tripId = in.readInt();
        this.userName = in.readString();
        this.dropAddress = in.readString();
        this.tripStatus = in.readString();
        this.pickupLocation = in.readString();
        this.drop_loc = in.readString();
    }

    public static final Creator<FCMDetials> CREATOR = new Creator<FCMDetials>() {
        @Override
        public FCMDetials createFromParcel(Parcel source) {
            return new FCMDetials(source);
        }

        @Override
        public FCMDetials[] newArray(int size) {
            return new FCMDetials[size];
        }
    };

    public static Creator<FCMDetials> getCREATOR() {
        return CREATOR;
    }
}
