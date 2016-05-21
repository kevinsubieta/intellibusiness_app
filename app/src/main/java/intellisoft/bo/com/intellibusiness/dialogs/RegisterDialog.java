package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteRegister;
import intellisoft.bo.com.intellibusiness.tasks.TaskRegisterUser;
import intellisoft.bo.com.intellibusiness.ui.MainActivity;

/**
 * Created by Subieta on 04/04/2016.
 */
public class RegisterDialog extends Dialog implements OnCompleteRegister {
    private Context context;
    private EditText etUserName;
    private EditText etCI;
    private EditText etName;
    private EditText etLastName;
    private EditText etMail;
    private EditText etTelf;
    private EditText etAddress;
    private EditText etPassword;
    private EditText etRepeatPassword;

    public RegisterDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_register_user);
        setCancelable(false);
        initDialogComponents();
    }

    private void initDialogComponents(){
        this.etUserName = (EditText) findViewById(R.id.etUserName);
        this.etCI = (EditText) findViewById(R.id.etCI);
        this.etName = (EditText) findViewById(R.id.etName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etMail = (EditText) findViewById(R.id.etMail);
        this.etTelf = (EditText) findViewById(R.id.etTelf);
        this.etAddress = (EditText) findViewById(R.id.etAddress);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.etRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);

        (findViewById(R.id.btnDialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });

        (findViewById(R.id.btnDialogCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initRegister(){
        if(validateText(etUserName) && validateNumbers(etCI) && validateText(etName)
                && validateText(etLastName) && validateEmail(etMail) && validateNumbers(etTelf)
                && validateText(etAddress) && verifyPasswords(etPassword,etRepeatPassword)){

            Usuario usuario = new Usuario(etUserName.getText().toString(),
                                        etPassword.getText().toString(),
                                        Integer.parseInt(etCI.getText().toString()),
                                        etName.getText().toString(),
                                        etLastName.getText().toString(),
                                        etMail.getText().toString(),
                                        etTelf.getText().toString(),
                                        etAddress.getText().toString(),
                                        null);

            new TaskRegisterUser (context,this,usuario).execute();
        } else {
          this.onIncorrectRegister();
        }
    }

    private boolean validateText(EditText editText){
        return editText.getText().toString() !="" ? true : false;
    }

    private boolean validateNumbers(EditText editText){
        return TextUtils.isDigitsOnly(editText.getText()) ? true : false;
    }

    private boolean validateEmail(EditText editText){
        return editText.getText().toString().contains("@") ? true : false;
    }

    private boolean verifyPasswords(EditText editText1, EditText editText2){
        return editText1.getText().toString().equals(editText2.getText().toString()) ? true : false;
    }

    @Override
    public void onCompleteRegister() {
        context.startActivity(new Intent(context,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK ));
    }

    @Override
    public void onIncorrectRegister() {
        Toast.makeText(context,context.getResources().getString(R.string.dialog_register_user_msjIncorrect),
                Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorRegister() {
        Toast.makeText(context,context.getResources().getString(R.string.dialog_register_user_msjError),
                Toast.LENGTH_SHORT);
    }
}
