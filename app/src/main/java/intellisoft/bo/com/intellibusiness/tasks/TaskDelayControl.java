package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;

import intellisoft.bo.com.intellibusiness.ui.LoginActivity;
import intellisoft.bo.com.intellibusiness.ui.MainActivity;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSplash;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by Subieta on 29/03/2016.
 */
public class TaskDelayControl extends AsyncTask<Void,String,String>{

    private Context context;
    private PreferencesManager preferencesManager;
    private OnCompleteSplash onCompleteSplash;

    public TaskDelayControl(Context context,OnCompleteSplash onCompleteSplash) {
        this.context = context;
        this.onCompleteSplash = onCompleteSplash;
        preferencesManager = new PreferencesManager(context);
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            Thread.sleep(AppStatics.initialAppDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return preferencesManager.getUserTokken();
    }



    @Override
    protected void onPostExecute(String result) {
        if(!result.equals("null")){
            onCompleteSplash.onCorrectSplash(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK ));
        } else {
            onCompleteSplash.onCorrectSplash(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK ));
        }
        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {
        onCompleteSplash.onCancelledSplash();
        super.onCancelled();
    }
}
