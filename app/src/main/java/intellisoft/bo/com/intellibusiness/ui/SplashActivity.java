package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSplash;
import intellisoft.bo.com.intellibusiness.tasks.TaskDelayControl;
import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by Subieta on 29/03/2016.
 */
public class SplashActivity extends Activity implements OnCompleteSplash{

    private TaskDelayControl taskDelayControl;
    private final String TAG = "intellisoft.bo.com.intellibusiness.ui.SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initSplash();
    }

    private void initSplash() {
        taskDelayControl= new TaskDelayControl(SplashActivity.this,SplashActivity.this);
        taskDelayControl.execute();
    }

    @Override
    public void onBackPressed() {
        taskDelayControl.cancel(true);
    }

    /**
     * Lanza el intent que recibe
     * @param intent (intent que recibido de la tarea asíncrona.)
     */
    @Override
    public void onCorrectSplash(Intent intent) {
        Log.d(TAG, getResources().getString(R.string.splash_activity_msjok));
        startActivity(intent);
    }

    /**
     * Si se cancela la tarea asíncrona, se elimina la tarea y nos salimos de app.
     */
    @Override
    public void onCancelledSplash() {
        Log.d(TAG, getResources().getString(R.string.splash_activity_msjcancel));
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
