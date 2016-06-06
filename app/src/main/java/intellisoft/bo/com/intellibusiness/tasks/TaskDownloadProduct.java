package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadProd;

/**
 * Created by kevin on 05/06/2016.
 */
public class TaskDownloadProduct extends AsyncTask<Void,Void,ProductoEmpresa> {
    private Context context;
    private OnCompleteDownloadProd onCompleteDownloadProd;
    private int idp;

    public TaskDownloadProduct(Context context, OnCompleteDownloadProd onCompleteDownloadProd,int idp) {
        this.context = context;
        this.onCompleteDownloadProd = onCompleteDownloadProd;
        this.idp = idp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ProductoEmpresa doInBackground(Void... params) {
        Services services = new Services(context);
        try {
            return services.getProductById(idp);
        }catch (Exception e){
            return null;
        }

    }


    @Override
    protected void onPostExecute(ProductoEmpresa productoEmpresa) {
        if(productoEmpresa!=null){
            onCompleteDownloadProd.onCompleteDownload(productoEmpresa);
        }else{
            onCompleteDownloadProd.onErrorDownload();
        }
        super.onPostExecute(productoEmpresa);
    }
}
