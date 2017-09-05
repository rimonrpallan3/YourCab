package com.voyager.user.yourcab.otppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.voyager.user.yourcab.DocumentPage.DocumentPage;
import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.TermsAndConduction.TermsAndConduction;
import com.voyager.user.yourcab.otppage.presenter.OTPPresenter;
import com.voyager.user.yourcab.otppage.view.IOTPView;

/**
 * Created by User on 8/30/2017.
 */

public class OTPPage extends AppCompatActivity implements IOTPView{

    OTPPresenter otpPresenter;
    Button btnSubmit;
    TextView optSecondMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        optSecondMsg = (TextView) findViewById(R.id.optSecondMsg);
        otpPresenter = new OTPPresenter(this,this);

        SpannableString ss = new SpannableString(getString(R.string.otp_secondtxt_msg));
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(final View textView) {
                Intent intent = new Intent(OTPPage.this, TermsAndConduction.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(ContextCompat.getColor(getApplicationContext(), R.color.highLightText));
                textPaint.setUnderlineText(true);
            }
        };

        ss.setSpan(clickableSpan,59,76, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        optSecondMsg.setText(ss);
        optSecondMsg.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void setTermsAndConductionText() {
        SpannableString ss = otpPresenter.getTextClickable(this);
        System.out.println("setTermsAndConductionText- String: "+ss);
    }

    @Override
    public void moveToMainView() {

    }

    public void  btnSubmit(View v){
        Intent intent = new Intent(OTPPage.this, DocumentPage.class);
        startActivity(intent);
        finish();
    }
}
