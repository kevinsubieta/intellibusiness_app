package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.tasks.TaskLoginUser;

/**
 * Created by Subieta on 30/03/2016.
 */
public class LoadingDialog extends ProgressDialog {
    private TaskLoginUser taskLoginUser;


    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context,TaskLoginUser taskLoginUser, int themeResId) {
        super(context, themeResId);
        this.taskLoginUser = taskLoginUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMessage(getContext().getResources().getString(R.string.dialog_loading_tvLoading));
        setCancelable(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        taskLoginUser.cancel(true);
        super.onBackPressed();
    }
}
