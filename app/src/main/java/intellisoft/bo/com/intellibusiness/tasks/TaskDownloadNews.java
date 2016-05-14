package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.inventario.ImagenProducto;
import intellisoft.bo.com.intellibusiness.entity.inventario.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.marketing.Descuento;
import intellisoft.bo.com.intellibusiness.entity.marketing.ProductoDescuento;
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
            for (int i = 0; i<10; i++ ){
                Descuento descuento = new Descuento("Description: "+i,i,i+1,i,false );
                ProductoDescuento productoDescuento = new ProductoDescuento(i,i,descuento);
                ImagenProducto imagenProducto = new ImagenProducto("https://cdn4.iconfinder.com/data/icons/pretty_office_3/256/packing.png");

                List<ProductoDescuento> lstProductoDescuento = new ArrayList<>();
                lstProductoDescuento.add(productoDescuento);

                List<ImagenProducto> lstImagenProductos = new ArrayList<>();
                lstImagenProductos.add(imagenProducto);
                ProductoEmpresa productoEmpresa = new ProductoEmpresa("Soy el producto: "+i,"Terbol","Terbol",1,i,1,"detalle",lstProductoDescuento, lstImagenProductos);
                lstProductoEmpresas.add(productoEmpresa);
            }
        return lstProductoEmpresas;
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
