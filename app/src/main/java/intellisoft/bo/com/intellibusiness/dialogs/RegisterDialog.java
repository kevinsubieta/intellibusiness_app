package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by Subieta on 04/04/2016.
 */
public class RegisterDialog extends Dialog {

    public RegisterDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_register_user);
    }
}
