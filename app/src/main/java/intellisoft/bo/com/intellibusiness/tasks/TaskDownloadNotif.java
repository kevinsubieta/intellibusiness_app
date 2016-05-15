package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.app.Notifications;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNotif;

/**
 * Created by Subieta on 15/05/2016.
 */
public class TaskDownloadNotif extends AsyncTask<Void,Void,List<Notifications>> {
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
    protected List<Notifications> doInBackground(Void... params) {
        List<Notifications> lstNotifications = new ArrayList<>();

        lstNotifications.add(new Notifications("Notificación 1","16/05/2016","http://www.formagazin.hu/wp-content/themes/corporatebusiness/images/customers/14.png"));
        lstNotifications.add(new Notifications("Notificación 2","16/05/2016"));
        lstNotifications.add(new Notifications("Notificación 2","16/05/2016","http://www.formagazin.hu/wp-content/themes/corporatebusiness/images/customers/14.png"));

        return lstNotifications;
    }

    @Override
    protected void onPostExecute(List<Notifications> lstNotifications) {
        if(lstNotifications!=null){
            onCompleteDownloadNotif.onCorrectDownload(lstNotifications);
        }else {
            onCompleteDownloadNotif.onErrorDownload();
        }
        swipeRefreshLayout.setRefreshing(false);
        super.onPostExecute(lstNotifications);
    }
}
