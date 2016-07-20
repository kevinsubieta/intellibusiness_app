package intellisoft.bo.com.intellibusiness.components.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;

import org.w3c.dom.Text;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.ui.DetailProductActivity;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private List<ProductoEmpresa> lstProductoEmpresas;
    private AQuery aQuery;

    public ProductoAdapter(Context context, List<ProductoEmpresa> lstProductoEmpresas) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewHolder listViewHolder = new ListViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_product,null,true);
        listViewHolder.ivProduct = (ImageView) convertView.findViewById(R.id.ivProduct);
        listViewHolder.tvNameProduct = (TextView) convertView.findViewById(R.id.tvNameProduct);
        listViewHolder.tvPriceProduct = (TextView) convertView.findViewById(R.id.tvPriceProduct);
        listViewHolder.rlDescuento = (RelativeLayout) convertView.findViewById(R.id.rlDescuento);
        listViewHolder.tvDescuento = (TextView) convertView.findViewById(R.id.tvDesc);

        if(lstProductoEmpresas.get(position).getLstImgProducto().size()>0 &&
                lstProductoEmpresas.get(position).getLstImgProducto().get(0).getUrl()!=null){
            aQuery.id(listViewHolder.ivProduct).image(lstProductoEmpresas.get(position).getLstImgProducto().get(0).getUrl());
        }

        if(lstProductoEmpresas.get(position).getLstProductoDes()!=null &&
                lstProductoEmpresas.get(position).getLstProductoDes().size() >0 ){
            listViewHolder.rlDescuento.setVisibility(View.VISIBLE);
            listViewHolder.tvDescuento.setText("-"+lstProductoEmpresas.get(position).getLstProductoDes().
                    get(0).
                    getInsDescuento().getPorcentaje()+"%");
        }

        listViewHolder.tvNameProduct.setText(lstProductoEmpresas.get(position).getNombre().toString());
        listViewHolder.tvPriceProduct.setText(lstProductoEmpresas.get(position).getPrecio().toString() +" "+ AppStatics.MONEDA);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("ProductoEmpresa",lstProductoEmpresas.get(position));
                context.startActivity(intent);

            }
        });
        return convertView;
    }


    static class ListViewHolder{
        ImageView ivProduct;
        TextView tvNameProduct;
        TextView tvPriceProduct;
        RelativeLayout rlDescuento;
        TextView tvDescuento;
    }
}
