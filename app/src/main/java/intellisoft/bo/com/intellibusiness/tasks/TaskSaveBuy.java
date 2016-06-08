package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.adm.Cliente;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSaveBuy;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by kevin on 05/06/2016.
 */
public class TaskSaveBuy extends AsyncTask<Void,Void,Boolean> {
    private Context context;
    private OnCompleteSaveBuy onCompleteSaveBuy;
    private ProductoEmpresa productoEmpresa;
    private int cantidad;


    public TaskSaveBuy(Context context, OnCompleteSaveBuy onCompleteSaveBuy, ProductoEmpresa productoEmpresa,int cantidad) {
        this.context = context;
        this.onCompleteSaveBuy = onCompleteSaveBuy;
        this.productoEmpresa = productoEmpresa;
        this.cantidad = cantidad;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Services services = new Services(context);
        try {
            Usuario cliente = new PreferencesManager(context).getUsuario();
            return services.saveBuyClient(cliente.getId(),productoEmpresa.getProducto(),productoEmpresa.getPrecio(),
                    productoEmpresa.getCosto(),cantidad);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            onCompleteSaveBuy.onCompleteSaveBuy(result);
        }else{
            onCompleteSaveBuy.onErrorSaveBuy();
        }
        super.onPostExecute(result);
    }
}
