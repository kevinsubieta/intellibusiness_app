package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.dialogs.RegisterDialog;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteLogin;
import intellisoft.bo.com.intellibusiness.tasks.TaskLoginUser;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by Subieta on 30/03/2016.
 */
public class LoginActivity extends Activity implements OnCompleteLogin {

    private LoadingDialog loadingDialog;
    private TaskLoginUser taskLoginUser;
    private EditText la_etUserName;
    private ShowHidePasswordEditText la_etPassUser;

    private final String TAG = "intellisoft.bo.com.intellibusiness.ui.LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponentes();
    }

    public void clcBtnSignUp(View view){
        taskLoginUser = new TaskLoginUser(LoginActivity.this, LoginActivity.this,
                                        la_etUserName.getText().toString(),
                                        convertPass(la_etPassUser.getText().toString()));
        loadingDialog = new LoadingDialog(this,taskLoginUser,R.style.Theme_Dialog_loading);
        loadingDialog.show();
        taskLoginUser.execute();
    }

    private void initComponentes(){
        this.la_etUserName = (EditText) findViewById(R.id.la_etUserName);
        this.la_etPassUser = (ShowHidePasswordEditText) findViewById(R.id.la_etPassUser);
    }

    public void clcCreateNewAccount(View view){
        RegisterDialog registerDialog = new RegisterDialog(LoginActivity.this);
        registerDialog.show();
    }

    private String convertPass(String pass){
        return bin2hex(getHash(pass)).toLowerCase();
    }

    private byte[] getHash(String password) {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes());
    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
    }


    @Override
    public void onCorrectLoggin(Usuario usuario) {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogOk));
        loadingDialog.dismiss();
        new PreferencesManager(LoginActivity.this).setUsuario(usuario);
        startActivity(new Intent(LoginActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK ));
    }

    @Override
    public void onIncorrectLoggin() {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogNO));
        loadingDialog.dismiss();
        Toast.makeText(LoginActivity.this,getResources().getString(R.string.login_activity_msjLogNO),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelLoggin() {
        Log.d(TAG, getResources().getString(R.string.login_activity_msjLogCancel));
        loadingDialog.dismiss();
    }
}


