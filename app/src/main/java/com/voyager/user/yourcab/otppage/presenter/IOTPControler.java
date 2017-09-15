package com.voyager.user.yourcab.otppage.presenter;

import android.content.Context;
import android.text.SpannableString;
import android.widget.TextView;

/**
 * Created by User on 8/30/2017.
 */

public interface IOTPControler {

    void setOPTSecondMsg(TextView v);
    void doOTPValidationAndCheck(String edtOPTNo,Boolean checkTermsAndConductionBox);
}
