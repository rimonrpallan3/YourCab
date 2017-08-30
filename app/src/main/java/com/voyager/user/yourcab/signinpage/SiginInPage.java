package com.voyager.user.yourcab.signinpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.landingpage.LandingPage;
import com.voyager.user.yourcab.registerpage.RegisterPage;
import com.voyager.user.yourcab.signinpage.presenter.SignInPresenter;
import com.voyager.user.yourcab.signinpage.view.ISignInView;

/**
 * Created by User on 8/23/2017.
 */

public class SiginInPage extends AppCompatActivity implements ISignInView{

    private EditText edtEmailPhno;
    private EditText edtPswd;
    private Button   btnSubmit;

    private ProgressBar progressBar;

    SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        //find view
        edtEmailPhno = (EditText) this.findViewById(R.id.edtEmailPhno);
        edtPswd = (EditText) this.findViewById(R.id.edtPswd);
        btnSubmit = (Button) this.findViewById(R.id.btnSubmit);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

        //init
        signInPresenter = new SignInPresenter(this);
        signInPresenter.setProgressBarVisiblity(View.INVISIBLE);
   }

   public void btnSubmit(View v){
       signInPresenter.setProgressBarVisiblity(View.VISIBLE);
       btnSubmit.setEnabled(false);
       signInPresenter.doLogin(edtEmailPhno.getText().toString(), edtPswd.getText().toString());
   }

    public void SignUp(View v){
        Intent intent = new Intent(this, RegisterPage.class);
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
            Intent intent = new Intent(this, LandingPage.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
