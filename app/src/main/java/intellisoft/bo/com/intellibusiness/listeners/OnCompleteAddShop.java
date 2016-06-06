package intellisoft.bo.com.intellibusiness.listeners;

import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by kevin on 04/06/2016.
 */
public interface OnCompleteAddShop {
    void onCorrectAddCart(Boolean result);
    void onErrorAddCart();
}
