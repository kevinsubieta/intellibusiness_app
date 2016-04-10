package intellisoft.bo.com.intellibusiness.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Subieta on 29/03/2016.
 */
public class PreferencesManager {

    //-------------- Name of preferences directory--------------------//
    private final String PREFERENCE_CLIENT = "CLIENT_PREFERENCE";

    //-------------- Name of preferences directory--------------------//
    private final String USER_TOKKEN = "USER_REGISTRED";
    //---------------------------------------------------------------//

    private Context context;

    public PreferencesManager(Context context){
        this.context = context;
    }

    public void setUserTokken(String tokken){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKKEN,tokken!=null ? tokken : "null");
    }

    public String getUserTokken(){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        return prefs.getString(USER_TOKKEN,"null");
    }
}
