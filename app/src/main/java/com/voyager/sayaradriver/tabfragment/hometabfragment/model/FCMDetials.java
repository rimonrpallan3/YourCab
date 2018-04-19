package com.voyager.sayaradriver.tabfragment.hometabfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 15-Mar-18.
 */

public class FCMDetials implements Parcelable {
    String fare;
    String payType;
    String distance;
    String pickupAddress;
    String tripId;
    String userName;
    String dropAddress;
    String tripStatus;

    protected FCMDetials(Parcel in) {
        fare = in.readString();
        payType = in.readString();
        distance = in.readString();
        pickupAddress = in.readString();
        tripId = in.readString();
        userName = in.readString();
        dropAddress = in.readString();
        tripStatus = in.readString();
    }

    public static final Creator<FCMDetials> CREATOR = new Creator<FCMDetials>() {
        @Override
        public FCMDetials createFromParcel(Parcel in) {
            return new FCMDetials(in);
        }

        @Override
        public FCMDetials[] newArray(int size) {
            return new FCMDetials[size];
        }
    };

    @Override
    public int describeContents() {
        System.out.println("Describe the content DriverUserModel");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        System.out.println("writeToParcel to DriverUserModel ");
        dest.writeString(fare);
        dest.writeString(payType);
        dest.writeString(distance);
        dest.writeString(pickupAddress);
        dest.writeString(tripId);
        dest.writeString(userName);
        dest.writeString(dropAddress);
        dest.writeString(tripStatus);
    }

    public static Creator<FCMDetials> getCREATOR() {
        return CREATOR;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
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
}
