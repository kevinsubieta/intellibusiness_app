package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadCart;
import intellisoft.bo.com.intellibusiness.ui.ShopCartActivity;

/**
 * Created by kevin on 28/05/2016.
 */
public class TaskDeleteCart extends AsyncTask<Void, Void,List<ProductoEmpresa>> {
    private Context context;
    private LoadingDialog loadingDialog;
    private OnCompleteDownloadCart onCompleteDownloadCart;

    public TaskDeleteCart(Context context, OnCompleteDownloadCart onCompleteDownloadCart) {
        this.context = context;
        this.onCompleteDownloadCart = onCompleteDownloadCart;
    }

    @Override
    protected void onPreExecute() {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
        super.onPreExecute();
    }

    @Override
    protected List<ProductoEmpresa> doInBackground(Void... params) {
        Services services = new Services(context);
        List<ProductoEmpresa> lstProductoEmpresas = services.deleteShopCart(ShopCartActivity.lstShopForDelete);
        if(lstProductoEmpresas!=null){
            return lstProductoEmpresas;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<ProductoEmpresa> lstCarts) {
        loadingDialog.dismiss();
        if (lstCarts != null) {
            onCompleteDownloadCart.onCorrectDeleted(lstCarts);
        } else {
            onCompleteDownloadCart.onErrorDownload(2);
        }
        super.onPostExecute(lstCarts);
    }
}
