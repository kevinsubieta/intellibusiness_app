package intellisoft.bo.com.intellibusiness.components.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import org.w3c.dom.Text;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.inventario.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.ui.DetailProductActivity;

/**
 * Created by kevin on 13/05/2016.
 */
public class NewersAdapter extends BaseAdapter {
    private List<ProductoEmpresa> lstProductoEmpresas;
    private Context context;
    private AQuery aQuery;

    public NewersAdapter(Context context, List<ProductoEmpresa> lstProductoEmpresas) {
        this.context = context;
        this.lstProductoEmpresas = lstProductoEmpresas;
        this.aQuery = new AQuery(context);
    }

    @Override
    public int getCount() {
        return lstProductoEmpresas.size();
    }

    @Override
    public Object getItem(int position) {
        return lstProductoEmpresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeaderGridViewHolder viewHolder = new HeaderGridViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_product_offer,null,true);
        viewHolder.ivImageOffer = (ImageView) convertView.findViewById(R.id.ivImageOffer);
        viewHolder.tvDescriptionOffer = (TextView) convertView.findViewById(R.id.tvDescriptionOffer);
        viewHolder.tvPriceNewer = (TextView) convertView.findViewById(R.id.tvPriceNewer);

        aQuery.id(viewHolder.ivImageOffer).image(lstProductoEmpresas.get(position).getLstImagenProducto().get(0).getUrl());
        viewHolder.tvDescriptionOffer.setText(lstProductoEmpresas.get(position).getNombre());
        viewHolder.tvPriceNewer.setText(Double.toString(lstProductoEmpresas.get(position).getPrecio()));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailProductActivity.class));
            }
        });
        return convertView;
    }

    static class HeaderGridViewHolder{
        ImageView ivImageOffer;
        TextView tvDescriptionOffer;
        TextView tvPriceNewer;
    }
}
