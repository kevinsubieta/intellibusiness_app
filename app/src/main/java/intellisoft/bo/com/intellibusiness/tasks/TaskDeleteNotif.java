package intellisoft.bo.com.intellibusiness.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import intellisoft.bo.com.intellibusiness.consume.Services;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNotif;
import intellisoft.bo.com.intellibusiness.ui.InboxActivity;

/**
 * Created by kevin on 27/05/2016.
 */
public class TaskDeleteNotif extends AsyncTask<Void,Void,List<Inbox>> {
    private Context context;
    private LoadingDialog loadingDialog;
    private OnCompleteDownloadNotif onCompleteDownloadNotif ;

    public TaskDeleteNotif(Context context,OnCompleteDownloadNotif onCompleteDownloadNotif){
        this.context = context;
        this.onCompleteDownloadNotif = onCompleteDownloadNotif;
    }

    @Override
    protected void onPreExecute() {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
        super.onPreExecute();
    }

    @Override
    protected List<Inbox> doInBackground(Void... params) {
        Services services = new Services(context);
        List<Inbox> inbox = services.deleteNotification(InboxActivity.lstInboxForDelete);
        if(inbox !=null){
            return inbox;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Inbox> lstInbox) {
        loadingDialog.dismiss();
        if(lstInbox!=null){
            onCompleteDownloadNotif.onCorrectDelete(lstInbox);
        } else {
            onCompleteDownloadNotif.onErrorDownload(2);
        }
        super.onPostExecute(lstInbox);
    }
}
