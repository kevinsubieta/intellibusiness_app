package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSplash;
import intellisoft.bo.com.intellibusiness.tasks.TaskDelayControl;
import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by Subieta on 29/03/2016.
 */
public class SplashActivity extends AwesomeSplash implements OnCompleteSplash{

    private TaskDelayControl taskDelayControl;
    private final String TAG = "intellisoft.bo.com.intellibusiness.ui.SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        initSplash();
    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.blue_main); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(3000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.icon_ib4); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.DropOut); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path

        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(2500);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorBlack); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(2500);
        configSplash.setPathSplashFillColor(R.color.colorBlack); //path object filling color


        //Customize Title
        configSplash.setTitleSplash(getResources().getString(R.string.app_name));
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(32f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.DropOut);
    }

    @Override
    public void animationsFinished() {
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
