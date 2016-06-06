package intellisoft.bo.com.intellibusiness.listeners;

import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by kevin on 05/06/2016.
 */
public interface OnCompleteDownloadProd {
    void onCompleteDownload(ProductoEmpresa productoEmpresa);
    void onErrorDownload();
}
