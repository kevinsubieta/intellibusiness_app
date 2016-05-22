package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteLogin;

/**
 * Created by Subieta on 30/03/2016.
 */
public class TaskLoginUser extends AsyncTask<Void,Void,Usuario> {

    private Context context;
    private OnCompleteLogin onCompleteLogin;
    private String userName;
    private String password;

    public TaskLoginUser(Context context, OnCompleteLogin onCompleteLogin, String userName, String password) {
        this.context = context;
        this.onCompleteLogin = onCompleteLogin;
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected Usuario doInBackground(Void... params) {
        try {
            Services services = new Services(context);
            return services.validateClients(userName,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        if(usuario!=null){
            onCompleteLogin.onCorrectLoggin(usuario);
        } else{
            onCompleteLogin.onIncorrectLoggin();
        }

        super.onPostExecute(usuario);
    }

    @Override
    protected void onCancelled() {
        onCompleteLogin.onCancelLoggin();
        super.onCancelled();
    }
}
