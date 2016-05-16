package intellisoft.bo.com.intellibusiness.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import intellisoft.bo.com.intellibusiness.entity.administrativo.Usuario;

/**
 * Created by Subieta on 29/03/2016.
 */
public class PreferencesManager {

    //-------------- Name of preferences directory--------------------//
    private final String PREFERENCE_CLIENT = "CLIENT_PREFERENCE";

    //-------------- Name of preferences directory--------------------//
    private final String USER_TOKKEN = "USER_REGISTRED";
    //---------------------------------------------------------------//

    public static final String PROPERTY_CLIENT 		= "client";

    private Context context;
    private Gson builder;

    public PreferencesManager(Context context){
        this.context = context;
        builder = new GsonBuilder().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
    }

    public void setUserTokken(String tokken){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKKEN,tokken!=null ? tokken : "null");
        editor.commit();
    }

    public String getUserTokken(){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        return prefs.getString(USER_TOKKEN,"null");
    }

    public void setUsuario(Usuario usuario){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_CLIENT, usuario!=null?builder.toJson(usuario):"");
        editor.commit();
    }

    public Usuario getUsuario() {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_CLIENT, Context.MODE_PRIVATE);
        if (!prefs.getString(PROPERTY_CLIENT, "").isEmpty()) {
            return builder.fromJson(prefs.getString(PROPERTY_CLIENT, ""), Usuario.class);
        } else {
            return null;
        }
    }
}
