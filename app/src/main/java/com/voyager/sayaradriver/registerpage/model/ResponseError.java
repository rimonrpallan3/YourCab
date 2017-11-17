package com.voyager.sayaradriver.registerpage.model;

import com.google.gson.annotations.SerializedName;
import com.voyager.sayaradriver.test.MainClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17-Nov-17.
 */

public class ResponseError {

    @SerializedName("output")
    public List<ResponseError.ErrorClass> errorOutput = new ArrayList();

    public static class ErrorClass {
        @SerializedName("success")
        public String success="";
        @SerializedName("error_msg")
        public String error_msg="";

    }
}
