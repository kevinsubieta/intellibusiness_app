package intellisoft.bo.com.intellibusiness.consume;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.adm.Cliente;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.entity.mark.Notificacion;

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

    public Usuario registryUser(Usuario obj) throws Exception {
        String metodo = "/Adm/Cliente/guardar";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user", new Gson().toJson(obj));
        try {
            return  getByPost(metodo,Usuario.class,params);
        } catch (Exception e) {
            return null;
        }
    }

    public Cliente updateClient(Cliente obj) throws Exception {
        String metodo = "/Adm/Cliente/update";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("cliente", new Gson().toJson(obj));
        try {
            return  getByPost(metodo,Cliente.class,params);
        } catch (Exception e) {
            return null;
        }
    }


    public Usuario validateClients(String userName, String password) {
        String metodo = "/Adm/Cliente/Validate/" + userName + "/" + password;
        try{
            return get(metodo,Usuario.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Inbox> getInbox(int id) {
        String metodo = "/Adm/Cliente/Notifications/" + id;
        try{
            return getList(metodo,new TypeToken<ArrayList<Inbox>>(){}.getType());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Inbox> deleteNotification(List<Inbox> lstInbox){
        String metodo = "/Adm/Cliente/DeleteNotifications";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("lstInbox",new Gson().toJson(lstInbox));
        try{
           return getListByPost(metodo,params,new TypeToken<ArrayList<Inbox>>(){}.getType());
        }catch (Exception e){
            return null;
        }
    }

    public List<ProductoEmpresa> getShopCart(int id) {
        String metodo = "/Adm/Cliente/ShopCart/" + id;
        try{
            return getList(metodo,new TypeToken<ArrayList<ProductoEmpresa>>(){}.getType());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductoEmpresa> deleteShopCart(List<ProductoEmpresa> lstShopCart){
        String metodo = "/Adm/Cliente/DeleteShopCart";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("lstShop",new Gson().toJson(lstShopCart));
        try{
            return getListByPost(metodo,params,new TypeToken<ArrayList<ProductoEmpresa>>(){}.getType());
        }catch (Exception e){
            return null;
        }
    }



}