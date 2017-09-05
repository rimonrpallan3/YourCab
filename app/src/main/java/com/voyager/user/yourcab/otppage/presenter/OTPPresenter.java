package com.voyager.user.yourcab.otppage.presenter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.otppage.view.IOTPView;

/**
 * Created by User on 8/30/2017.
 */

public class OTPPresenter implements IOTPControler {

    IOTPView iotpView;
    Context context;


    public OTPPresenter(IOTPView iotpView, Context context) {
        this.iotpView = iotpView;
        this.context = context;
    }

    @Override
    public SpannableString getTextClickable(Context context) {
        System.out.println("getTextClickable");
        SpannableString ss = new SpannableString(context.getString(R.string.otp_secondtxt_msg));
        String startIndex =". ";
        String lastIndex =".";
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                iotpView.moveToMainView();
            }

        };
        ss.setSpan(clickableSpan,58,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //ss.setSpan(clickableSpan, 22, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
