package com.voyager.sayaradriver.tabfragment.profiletabfragment.model;

import java.util.List;

/**
 * Created by User on 09-Feb-18.
 */

public class ProfileModel {

    String driverName;
    String driverPhone;
    String driverCity;
    String driverCountry;
    String driverCpr;
    String driverPhoto;
    int driverId;
    String driverRating;
    String carPhoto;
    String carName;

    List<Documents> documents;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverCity() {
        return driverCity;
    }

    public void setDriverCity(String driverCity) {
        this.driverCity = driverCity;
    }

    public String getDriverCountry() {
        return driverCountry;
    }

    public void setDriverCountry(String driverCountry) {
        this.driverCountry = driverCountry;
    }

    public String getDriverCpr() {
        return driverCpr;
    }

    public void setDriverCpr(String driverCpr) {
        this.driverCpr = driverCpr;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }
}
