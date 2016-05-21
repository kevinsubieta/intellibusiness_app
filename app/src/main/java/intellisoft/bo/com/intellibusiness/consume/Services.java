package intellisoft.bo.com.intellibusiness.consume;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import intellisoft.bo.com.intellibusiness.entity.adm.Cliente;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;

/**
 * Created by kevin on 21/05/2016.
 */
public class Services extends Web {

    private Context _context;
    private Gson builder;

    public Services(Context context) {
        super(context);
        this._context = _context;
        builder = new GsonBuilder().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
    }

    public Usuario registry(Usuario obj) throws Exception {
        String metodo = "/Adm/Cliente/guardar";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user", new Gson().toJson(obj));
        try {
            return getByPost(metodo, Usuario.class, params);
        } catch (Exception e) {
            return null;
        }
    }

}