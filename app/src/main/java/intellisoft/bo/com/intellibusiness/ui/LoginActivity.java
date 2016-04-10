package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.dialogs.RegisterDialog;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteLogin;
import intellisoft.bo.com.intellibusiness.tasks.TaskLoginUser;

/**
 * Created by Subieta on 30/03/2016.
 */
public class LoginActivity extends Activity implements OnCompleteLogin {

    private LoadingDialog loadingDialog;
    private TaskLoginUser taskLoginUser;
    private final String TAG = "intellisoft.bo.com.intellibusiness.ui.LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clcBtnSignUp(View view){
        taskLoginUser = new TaskLoginUser(LoginActivity.this, LoginActivity.this);
        loadingDialog = new LoadingDialog(this,taskLoginUser,R.style.Theme_Dialog_loading);
        loadingDialog.show();
        taskLoginUser.execute();
    }

    public void clcCreateNewAccount(View view){
        RegisterDialog registerDialog = new RegisterDialog(LoginActivity.this);
        registerDialog.show();
    }


    @Override
    public void onCorrectLoggin() {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogOk));
        loadingDialog.dismiss();
    }

    @Override
    public void onIncorrectLoggin() {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogNO));
        loadingDialog.dismiss();
    }

    @Override
    public void onCancelLoggin() {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogCancel));
        loadingDialog.dismiss();
    }
}


