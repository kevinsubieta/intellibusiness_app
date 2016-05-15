package intellisoft.bo.com.intellibusiness.listeners;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inventario.ProductoEmpresa;

/**
 * Created by Subieta on 14/05/2016.
 */
public interface OnCompleteDownloadCart {
    void onCorrectDownload(List<ShoppingCart> lstShoppingCarts);
    void onErrorDownload();
}