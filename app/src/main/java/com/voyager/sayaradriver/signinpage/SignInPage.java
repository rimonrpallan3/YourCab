package com.voyager.sayaradriver.signinpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.Helper;
import com.voyager.sayaradriver.firstotppage.FirstOTPPage;
import com.voyager.sayaradriver.landingpage.LandingPage;
import com.voyager.sayaradriver.registerpage.RegisterPage;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.signinpage.presenter.SignInPresenter;
import com.voyager.sayaradriver.signinpage.view.ISignInView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 8/23/2017.
 */

public class SignInPage extends AppCompatActivity implements ISignInView{

    private EditText edtEmailPhno;
    private EditText edtPswd;
    private Button   btnSubmit;

    private ProgressBar progressBar;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    SignInPresenter signInPresenter;
    String fireBaseToken="";

    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        ButterKnife.bind(this);

        fireBaseToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("----------- onCreate ----------fireBaseToken: "+fireBaseToken);

        sharedPrefs = getSharedPreferences(getResources().getString(R.string.myPreference),
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        //find view
        edtEmailPhno = (EditText) this.findViewById(R.id.edtEmailPhno);
        edtPswd = (EditText) this.findViewById(R.id.edtPswd);
        btnSubmit = (Button) this.findViewById(R.id.btnSubmit);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

        //init
        signInPresenter = new SignInPresenter(this,sharedPrefs,editor,this);
        signInPresenter.setProgressBarVisiblity(View.INVISIBLE);
   }

   public void btnSubmit(View v){
       Helper.hideKeyboard(this);
       signInPresenter.setProgressBarVisiblity(View.VISIBLE);
       btnSubmit.setEnabled(false);
       signInPresenter.doLogin(edtEmailPhno.getText().toString(), edtPswd.getText().toString(),fireBaseToken);
   }

    public void SignUp(View v){
        Intent intent = new Intent(this, FirstOTPPage.class);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onClearText() {
        edtEmailPhno.setText("");
        edtPswd.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        signInPresenter.setProgressBarVisiblity(View.INVISIBLE);
        edtEmailPhno.setEnabled(true);
        edtPswd.setEnabled(true);
        if (result){
        }
        else {
            Toast.makeText(this, "Please input Values, code = " + code, Toast.LENGTH_SHORT).show();
            btnSubmit.setEnabled(true);
            signInPresenter.clear();
        }
    }

    @Override
    public void onLoginResponse(Boolean result, int code) {
        signInPresenter.setProgressBarVisiblity(View.INVISIBLE);
        edtEmailPhno.setEnabled(true);
        edtPswd.setEnabled(true);
        if (result){
            signInPresenter.onLoginSucuess();
        }
        else {
            Toast.makeText(this, "Please input correct UserName and Password, code = " + code, Toast.LENGTH_SHORT).show();
            btnSubmit.setEnabled(true);
            signInPresenter.clear();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void sendPParcelableObj(DriverUserModel driverUserModel) {
        Intent intent = new Intent(this, LandingPage.class);
        intent.putExtra("DriverUserModel", driverUserModel);
        startActivity(intent);
        setResult(1);
        finish();
    }
}
