package com.voyager.sayaradriver.DocumentPage.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 05-Dec-17.
 */

public class DocModel {
    @SerializedName("error")
    public Boolean error;
    @SerializedName("driver_id")
    public String driverId;
    @SerializedName("success_msg")
    public String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
