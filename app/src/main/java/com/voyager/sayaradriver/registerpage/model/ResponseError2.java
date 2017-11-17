package com.voyager.sayaradriver.registerpage.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17-Nov-17.
 */

public class ResponseError2 {


        @SerializedName("error")
        public String error="";
        @SerializedName("driver_id")
        public String driver_id="";
        @SerializedName("created_at")
        public String created_at="";

        public String getError() {
                return error;
        }

        public void setError(String error) {
                this.error = error;
        }

        public String getDriver_id() {
                return driver_id;
        }

        public void setDriver_id(String driver_id) {
                this.driver_id = driver_id;
        }

        public String getCreated_at() {
                return created_at;
        }

        public void setCreated_at(String created_at) {
                this.created_at = created_at;
        }
}
