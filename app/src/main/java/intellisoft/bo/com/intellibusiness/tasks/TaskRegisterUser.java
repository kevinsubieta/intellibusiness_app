package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.entity.adm.Cliente;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteRegister;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by Subieta on 30/03/2016.
 */
public class TaskRegisterUser extends AsyncTask<Void,Void,Boolean>{
    private Context context;
    private OnCompleteRegister onCompleteRegister;
    private Usuario usuario;
    private LoadingDialog loadingDialog;
    private PreferencesManager preferencesManager;
    private final String TAG = "TaskRegisterUser";


    public TaskRegisterUser(Context context, OnCompleteRegister onCompleteRegister, Usuario usuario) {
        this.context = context;
        this.onCompleteRegister = onCompleteRegister;
        this.usuario = usuario;
        this.preferencesManager = new PreferencesManager(context);
    }

    @Override
    protected void onPreExecute() {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            usuario.setCliente(new Cliente("0"));
            Services services = new Services(context);
            if(services.registry(usuario) != null){
                preferencesManager.setUsuario(usuario);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        loadingDialog.dismiss();
        if(result) {
            onCompleteRegister.onCompleteRegister();
        } else {
            onCompleteRegister.onErrorRegister();
        }
        super.onPostExecute(result);
    }
}
