package intellisoft.bo.com.intellibusiness.push;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Subieta on 06/05/2016.
 */
public class GcmReceiveMessage extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d("", "From: " + from);
        Log.d("", "Message: " + message);
    }
}
