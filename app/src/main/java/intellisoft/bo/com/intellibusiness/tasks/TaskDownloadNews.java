package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.inv.ImagenProducto;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.mark.Descuento;
import intellisoft.bo.com.intellibusiness.entity.mark.ProductoDescuento;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNews;

/**
 * Created by Subieta on 13/05/2016.
 */
public class TaskDownloadNews extends AsyncTask<Void, Void,List<ProductoEmpresa>> {
    private Context context;
    private OnCompleteDownloadNews onCompleteDownloadNews;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TaskDownloadNews(Context context, OnCompleteDownloadNews onCompleteDownloadNews, SwipeRefreshLayout swipeRefreshLayout){
        this.context = context;
        this.onCompleteDownloadNews = onCompleteDownloadNews;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }


    @Override
    protected void onPreExecute() {
        swipeRefreshLayout.setRefreshing(true);
        super.onPreExecute();
    }

    @Override
    protected List<ProductoEmpresa> doInBackground(Void... params) {
        List<ProductoEmpresa> lstProductoEmpresas = new ArrayList<>();
        Services services = new Services(context);
        List<Integer> lstIndex =Arrays.asList(1, 2, 3, 4, 5);
        lstProductoEmpresas = services.getProductPage(lstIndex);
        if(lstProductoEmpresas!=null && lstProductoEmpresas.size()>0){
            return lstProductoEmpresas;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<ProductoEmpresa> productoEmpresas) {
        if(productoEmpresas!=null){
            onCompleteDownloadNews.onCorrectDownload(productoEmpresas);
        } else {
            onCompleteDownloadNews.onErrorDownload();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
