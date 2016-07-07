package intellisoft.bo.com.intellibusiness.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by kevin on 06/07/2016.
 */
public class NotificationDialog extends Dialog {
    private  String titulo;
    private String texto;
    private String imagen;

    public NotificationDialog(Context context) {
        super(context, R.style.Theme_Dialog_loading);
    }

    public NotificationDialog(Context context, String texto, String imagen, String titulo) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_notification);
        setCancelable(true);
        initDialogComponents();
    }

    private void initDialogComponents(){
        if(this.titulo!=null){
            ((TextView) findViewById(R.id.tvTittleDialog)).setText(titulo);
        }
        if(this.texto!=null){
            ((TextView) findViewById(R.id.tvMessage)).setText(texto);
        }
        if(this.imagen !=null){
            new AQuery(getContext()).id(R.id.ivNotifi).image(this.imagen);
        }

        ((ImageView) findViewById(R.id.ivCloseDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public void onBackPressed() {
        this.dismiss();
    }

    public void clcCloseDialog(View view){
        this.dismiss();
    }
}
