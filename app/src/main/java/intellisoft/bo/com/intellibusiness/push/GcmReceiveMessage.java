package intellisoft.bo.com.intellibusiness.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.ui.InboxActivity;
import intellisoft.bo.com.intellibusiness.ui.MainActivity;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;

/**
 * Created by Subieta on 06/05/2016.
 */
public class GcmReceiveMessage extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        sendNotification(data);
    }

    private void sendNotification(Bundle message) {
        Intent intent = new Intent(this, InboxActivity.class);
        intent.putExtra("message", message.getString("message"));
        intent.putExtra("obj", message.getString("obj"));
        intent.putExtra("type", message.getString("type"));
        sendNotification(intent, message.getString("message"));
    }

    private void sendNotification(Intent intent, String message) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String[] split = message.split("@@");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_new_launcher);
        mBuilder.setContentTitle(split[0]);
        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(split[1]));
        mBuilder.setContentText(split[1]);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setAutoCancel(true);
        Notification note = mBuilder.build();
        note.defaults |= Notification.DEFAULT_VIBRATE;
        note.defaults |= Notification.DEFAULT_SOUND;
        mNotificationManager.notify(AppStatics.NOTIFICATION_ID, note);
    }
}
