package intellisoft.bo.com.intellibusiness.components.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.dialogs.NotificationDialog;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.ui.InboxActivity;

/**
 * Created by Subieta on 14/05/2016.
 */
public class InboxAdapter extends BaseAdapter {
    Context context;
    List<Inbox> lstInbox;
    AQuery aQuery;

    public InboxAdapter(Context context, List<Inbox> lstInbox) {
        this.context = context;
        this.lstInbox = lstInbox;
        this.aQuery = new AQuery(context);
    }

    @Override
    public int getCount() {
        return lstInbox.size();
    }

    @Override
    public Object getItem(int position) {
        return lstInbox.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HeaderGridViewHolder viewHolder = new HeaderGridViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_inbox,null,true);
        viewHolder.ivImageNotif = (ImageView) convertView.findViewById(R.id.ivImageNotif);
        viewHolder.tvTittleNotif = (TextView) convertView.findViewById(R.id.tvTittleDialog);
        viewHolder.tvDateNotif = (TextView) convertView.findViewById(R.id.tvDateNotif);
        viewHolder.cbDelete = (CheckBox) convertView.findViewById(R.id.cbDelete);

        if(lstInbox.get(position).getNotification().getImagen()!= null){
            aQuery.id(viewHolder.ivImageNotif).image(lstInbox.get(position).getNotification().getImagen());
            viewHolder.tvTittleNotif.setText(lstInbox.get(position).getNotification().getTexto());
         //   viewHolder.tvDateNotif.setText(lstNotifications.get(position).getFecha());
        } else {
            aQuery.id(viewHolder.ivImageNotif).image(R.drawable.ic_notific_text);
            viewHolder.tvTittleNotif.setText(lstInbox.get(position).getNotification().getImagen());
         //   viewHolder.tvDateNotif.setText(lstNotifications.get(position).getFecha());
        }
        if(!InboxActivity.checkDelete){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificationDialog notificationDialog = new NotificationDialog(context,
                            lstInbox.get(position).getNotification().getTitulo(),
                            lstInbox.get(position).getNotification().getTexto(),
                            lstInbox.get(position).getNotification().getImagen());
                    notificationDialog.show();
                }
            });
        } else {
            convertView.setOnClickListener(null);
        }



        viewHolder.cbDelete.setVisibility(InboxActivity.checkDelete ? View.VISIBLE : View.GONE);

        viewHolder.cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(InboxActivity.checkDelete){
                    if(isChecked){
                        InboxActivity.lstInboxForDelete.add(lstInbox.get(position));
                    } else {
                        InboxActivity.lstInboxForDelete.remove(lstInbox.get(position));
                    }
                }

            }
        });

        return convertView;
    }


    static class HeaderGridViewHolder{
        ImageView ivImageNotif;
        TextView tvTittleNotif;
        TextView tvDateNotif;
        CheckBox cbDelete;
    }
}
