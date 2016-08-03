package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.regex.Pattern;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by kevin on 20/07/2016.
 */
public class InformationDialog extends Dialog {

    public InformationDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_information);
        setCancelable(true);
        initDialogComponents();
    }

    private void initDialogComponents(){
        ((ImageView) findViewById(R.id.ivCloseDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView tvEnlace = (TextView)findViewById(R.id.tvEnlace);
        tvEnlace.setMovementMethod(LinkMovementMethod.getInstance());

        tvEnlace.setText("http://54.191.255.20:82/");
        Linkify.addLinks(tvEnlace, Linkify.WEB_URLS);

    }


    @Override
    public void onBackPressed() {
        this.dismiss();
    }

    public void clcCloseDialog(View view){
        this.dismiss();
    }
}