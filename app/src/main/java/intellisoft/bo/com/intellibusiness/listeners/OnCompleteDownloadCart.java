package intellisoft.bo.com.intellibusiness.listeners;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.ven.CarritoProducto;

/**
 * Created by Subieta on 14/05/2016.
 */
public interface OnCompleteDownloadCart {
    void onCorrectDownload(List<CarritoProducto> lstShoppingCarts);
    void onCorrectDeleted(List<CarritoProducto> lstShoppingDel);
    void onErrorDownload(int type);
}
