package intellisoft.bo.com.intellibusiness.push;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Subieta on 06/05/2016.
 */
public class GcmReceiver extends com.google.android.gms.gcm.GcmReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(),
                RegistrationIntentService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

    }
}
