package com.voyager.sayaradriver.tabfragment.ratingstabfragment.model;

/**
 * Created by User on 09-Feb-18.
 */

public class RatingModel {
    String driverName;
    String driverCity;
    String driverPhoto;
    String driverRating;
    boolean error;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverCity() {
        return driverCity;
    }

    public void setDriverCity(String driverCity) {
        this.driverCity = driverCity;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
