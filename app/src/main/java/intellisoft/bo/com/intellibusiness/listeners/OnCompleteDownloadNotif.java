package intellisoft.bo.com.intellibusiness.listeners;

import java.util.List;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;

/**
 * Created by Subieta on 15/05/2016.
 */
 public interface OnCompleteDownloadNotif {
    void onCorrectDownload(List<Notifications> lstProductoEmpresas);
    void onErrorDownload();
}
