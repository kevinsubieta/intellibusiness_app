package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import intellisoft.bo.com.intellibusiness.listeners.OnCompleteLogin;

/**
 * Created by Subieta on 30/03/2016.
 */
public class TaskLoginUser extends AsyncTask<Void,Void,Void> {

    private Context context;
    private OnCompleteLogin onCompleteLogin;

    public TaskLoginUser(Context context, OnCompleteLogin onCompleteLogin) {
        this.context = context;
        this.onCompleteLogin = onCompleteLogin;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(5 *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onCompleteLogin.onCorrectLoggin();
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onCancelled() {
        onCompleteLogin.onCancelLoggin();
        super.onCancelled();
    }
}
