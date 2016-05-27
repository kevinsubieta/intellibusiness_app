package intellisoft.bo.com.intellibusiness.listeners;

import java.util.List;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.entity.mark.Notificacion;

/**
 * Created by Subieta on 15/05/2016.
 */
 public interface OnCompleteDownloadNotif {
    void onCorrectDownload(List<Inbox> lstProductoEmpresas);
    void onCorrectDelete(List<Inbox> lstInbox);
    void onErrorDownload(int type);
}
