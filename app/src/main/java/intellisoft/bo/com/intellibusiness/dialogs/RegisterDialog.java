package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.ui.MainActivity;

/**
 * Created by Subieta on 04/04/2016.
 */
public class RegisterDialog extends Dialog {
    private Context context;

    public RegisterDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_register_user);
        setCancelable(false);
        initDialogComponents();
    }

    private void initDialogComponents(){
        (findViewById(R.id.btnDialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(),MainActivity.class));
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
