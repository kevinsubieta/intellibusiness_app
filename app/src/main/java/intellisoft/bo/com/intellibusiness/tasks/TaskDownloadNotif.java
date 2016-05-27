package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.entity.mark.Notificacion;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNotif;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

/**
 * Created by Subieta on 15/05/2016.
 */
public class TaskDownloadNotif extends AsyncTask<Void,Void,List<Inbox>> {
    private Context context;
    private OnCompleteDownloadNotif onCompleteDownloadNotif;
    private SwipeRefreshLayout swipeRefreshLayout;


    public TaskDownloadNotif(Context context, OnCompleteDownloadNotif onCompleteDownloadNotif,
                             SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.onCompleteDownloadNotif = onCompleteDownloadNotif;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        swipeRefreshLayout.setRefreshing(true);
        super.onPreExecute();
    }

    @Override
    protected List<Inbox> doInBackground(Void... params) {
        List<Inbox> lstInbox = new ArrayList<>();
        PreferencesManager preferencesManager = new PreferencesManager(context);
        Services services = new Services(context);
        if(preferencesManager !=null){
            lstInbox = services.getInbox(preferencesManager.getUsuario().getId());
            return lstInbox;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Inbox> lstNotifications) {
        if(lstNotifications!=null){
            onCompleteDownloadNotif.onCorrectDownload(lstNotifications);
        }else {
            onCompleteDownloadNotif.onErrorDownload(1);
        }
        swipeRefreshLayout.setRefreshing(false);
        super.onPostExecute(lstNotifications);
    }
}
