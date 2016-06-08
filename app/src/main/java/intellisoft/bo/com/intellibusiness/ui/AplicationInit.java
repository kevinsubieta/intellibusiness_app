package intellisoft.bo.com.intellibusiness.ui;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by kevin on 08/06/2016.
 */

@ReportsCrashes(
        formUri = "https://dualbizapp.cloudant.com/acra-deventos/_design/acra-storage/_update/report",
        mode = ReportingInteractionMode.DIALOG,
        reportType = org.acra.sender.HttpSender.Type.JSON,
        httpMethod = org.acra.sender.HttpSender.Method.PUT,
        resToastText = R.string.crash_toast_text, // optional, displayed as soon as the crash occurs, before collecting data which can take a few seconds
        resDialogText = R.string.crash_dialog_text,
        resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
        resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, // optional. When defined, adds a user text field input with this text resource as a label
        resDialogOkToast = R.string.crash_dialog_ok_toast,
        formUriBasicAuthLogin = "stediffeenceatillettleff",
        formUriBasicAuthPassword = "e5923cb069134e65fd997d3c640ac6fed6f76633")
public class AplicationInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
    }
}
