package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by Subieta on 04/04/2016.
 */
public class RegisterDialog extends Dialog {

    public RegisterDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_register_user);
        initDialogComponents();
    }

    private void initDialogComponents(){
        (findViewById(R.id.btnDialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        (findViewById(R.id.btnDialogCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
