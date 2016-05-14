package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadCart;

/**
 * Created by Subieta on 14/05/2016.
 */
public class TaskDownloadCart extends AsyncTask<Void,Void,List<ShoppingCart>> {
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
        swipeRefreshLayout.setRefreshing(true);
        super.onPreExecute();
    }

    @Override
    protected List<ShoppingCart> doInBackground(Void... params) {
        List<ShoppingCart> lstShoppingCarts = new ArrayList<>();
        lstShoppingCarts.add(new ShoppingCart("http://www.golfideal.com/images/carrito-popup.png","carrito1",1.11));
        lstShoppingCarts.add(new ShoppingCart("http://www.golfideal.com/images/carrito-popup.png","carrito2",2.11));
        lstShoppingCarts.add(new ShoppingCart("http://www.golfideal.com/images/carrito-popup.png","carrito3",3.11));
        return lstShoppingCarts;
    }

    @Override
    protected void onPostExecute(List<ShoppingCart> lstShoppingCarts) {
        if(lstShoppingCarts!=null){
            onCompleteDownloadCart.onCorrectDownload(lstShoppingCarts);
        } else {
            onCompleteDownloadCart.onErrorDownload();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
