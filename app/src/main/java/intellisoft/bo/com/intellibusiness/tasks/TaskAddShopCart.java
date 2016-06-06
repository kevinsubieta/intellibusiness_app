package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteAddShop;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by kevin on 04/06/2016.
 */
public class TaskAddShopCart extends AsyncTask<Void,Void,Boolean> {
    private Context context;
    private OnCompleteAddShop onCompleteAddShop;
    private ProductoEmpresa productoEmpresa;

    public TaskAddShopCart(Context context, OnCompleteAddShop onCompleteAddShop, ProductoEmpresa productoEmpresa) {
        this.context = context;
        this.onCompleteAddShop = onCompleteAddShop;
        this.productoEmpresa = productoEmpresa;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Services services = new Services(context);
        PreferencesManager preferencesManager = new PreferencesManager(context);
        return services.saveProductToShopCart(preferencesManager.getUsuario().getId(),productoEmpresa.getId());
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            onCompleteAddShop.onCorrectAddCart(result);
        } else {
            onCompleteAddShop.onErrorAddCart();
        }
        super.onPostExecute(result);
    }
}
