package intellisoft.bo.com.intellibusiness.components.adapters;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;

/**
 * Created by Subieta on 14/05/2016.
 */
public class InboxAdapter extends BaseAdapter {
    Context context;
    List<Notifications> lstNotifications;
    AQuery aQuery;

    public InboxAdapter(Context context, List<Notifications> lstNotifications) {
        this.context = context;
        this.lstNotifications = lstNotifications;
        this.aQuery = new AQuery(context);
    }

    @Override
    public int getCount() {
        return lstNotifications.size();
    }

    @Override
    public Object getItem(int position) {
        return lstNotifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeaderGridViewHolder viewHolder = new HeaderGridViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_inbox,null,true);
        viewHolder.ivImageNotif = (ImageView) convertView.findViewById(R.id.ivImageNotif);
        viewHolder.tvTittleNotif = (TextView) convertView.findViewById(R.id.tvTittleNotif);
        viewHolder.tvDateNotif = (TextView) convertView.findViewById(R.id.tvDateNotif);

        if(lstNotifications.get(position).getImagen()!= null){
            aQuery.id(viewHolder.ivImageNotif).image(lstNotifications.get(position).getImagen());
            viewHolder.tvTittleNotif.setText(lstNotifications.get(position).getTitulo());
            viewHolder.tvDateNotif.setText(lstNotifications.get(position).getFecha());
        } else {
            aQuery.id(viewHolder.ivImageNotif).image(R.drawable.ic_notific_text);
            viewHolder.tvTittleNotif.setText(lstNotifications.get(position).getTitulo());
            viewHolder.tvDateNotif.setText(lstNotifications.get(position).getFecha());
        }
        return convertView;
    }


    static class HeaderGridViewHolder{
        ImageView ivImageNotif;
        TextView tvTittleNotif;
        TextView tvDateNotif;
    }
}
