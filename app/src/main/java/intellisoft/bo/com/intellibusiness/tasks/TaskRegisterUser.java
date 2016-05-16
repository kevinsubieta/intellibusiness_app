package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.entity.administrativo.Cliente;
import intellisoft.bo.com.intellibusiness.entity.administrativo.Usuario;
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
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
            String gcmRegID = gcm.register(context.getResources().getString(R.string.senderID));
            Log.d(TAG, "Device registered, registration GCM ID = " + gcmRegID);
            usuario.setCliente(new Cliente(gcmRegID));
            preferencesManager.setUsuario(usuario);
            //Registramos con  el servicio por un post.
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
