package intellisoft.bo.com.intellibusiness.listeners;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by Subieta on 13/05/2016.
 */
public interface OnCompleteDownloadNews {
    void onCorrectDownload(List<ProductoEmpresa> lstProductoEmpresas);
    void onErrorDownload();
}
