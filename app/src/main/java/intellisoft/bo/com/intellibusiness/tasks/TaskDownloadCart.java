package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.adm.Cliente;
import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.ven.CarritoProducto;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadCart;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by Subieta on 14/05/2016.
 */
public class TaskDownloadCart extends AsyncTask<Void,Void,List<CarritoProducto>> {
    private Context context;
    private OnCompleteDownloadCart onCompleteDownloadCart;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TaskDownloadCart(Context context, OnCompleteDownloadCart onCompleteDownloadCart,
                            SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.onCompleteDownloadCart = onCompleteDownloadCart;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected List<CarritoProducto> doInBackground(Void... params) {
        Services services = new Services(context);
        PreferencesManager preferencesManager = new PreferencesManager(context);
        List<CarritoProducto> lstProductoEmpresas = null;
        if(preferencesManager != null){
          lstProductoEmpresas = services.getShopCart(preferencesManager.getUsuario().getId());
        }
        return lstProductoEmpresas!=null ? lstProductoEmpresas : null;
    }

    @Override
    protected void onPostExecute(List<CarritoProducto> lstShoppingCarts) {
        if(lstShoppingCarts!=null){
            onCompleteDownloadCart.onCorrectDownload(lstShoppingCarts);
        } else {
            onCompleteDownloadCart.onErrorDownload(1);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
