package intellisoft.bo.com.intellibusiness.components.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.ven.CarritoProducto;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadProduct;
import intellisoft.bo.com.intellibusiness.ui.DetailProductActivity;
import intellisoft.bo.com.intellibusiness.ui.ShopCartActivity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    Context context;
    List<CarritoProducto> lstShoppingCarts;
    AQuery aQuery;

    public ShoppingCartAdapter(Context context, List<CarritoProducto> lstShoppingCarts) {
        this.context = context;
        this.lstShoppingCarts = lstShoppingCarts;
        this.aQuery = new AQuery(context);
    }

    @Override
    public int getCount() {
        return lstShoppingCarts.size();
    }

    @Override
    public Object getItem(int position) {
        return lstShoppingCarts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_shopcart,null,true);
        ListViewHolder holder = new ListViewHolder();
        holder.ivProduct = (ImageView) convertView.findViewById(R.id.ivProduct);
        holder.tvNameProduct = (TextView) convertView.findViewById(R.id.tvNameProduct);
        holder.tvPriceProduct = (TextView) convertView.findViewById(R.id.tvPriceProduct);
        holder.cbDelete = (CheckBox) convertView.findViewById(R.id.cbDelete);
        holder.rlDescuento = (RelativeLayout) convertView.findViewById(R.id.rlDescuento);
        holder.tvDescuento = (TextView) convertView.findViewById(R.id.tvDesc);

        if(lstShoppingCarts.get(position).getProductoEmpresa().getLstImgProducto() !=null &&
                lstShoppingCarts.get(position).getProductoEmpresa().getLstImgProducto().size()>0){
            aQuery.id(holder.ivProduct).image(lstShoppingCarts.get(position).getProductoEmpresa().getLstImgProducto().get(0).getUrl());
        }

        if(lstShoppingCarts.get(position).getProductoEmpresa().getLstProductoDes()!=null &&
                lstShoppingCarts.get(position).getProductoEmpresa().getLstProductoDes().size() >0 ){
            holder.rlDescuento.setVisibility(View.VISIBLE);
            holder.tvDescuento.setText("-"+lstShoppingCarts.get(position).getProductoEmpresa().getLstProductoDes().
                    get(lstShoppingCarts.get(position).getProductoEmpresa().getLstProductoDes().size()-1).
                    getInsDescuento().getPorcentaje()+"%");
        }
        holder.tvNameProduct.setText(lstShoppingCarts.get(position).getProductoEmpresa().getNombre());
        holder.tvPriceProduct.setText(lstShoppingCarts.get(position).getProductoEmpresa().getPrecio().toString() + " $u$");
        holder.cbDelete.setVisibility(ShopCartActivity.checkDelete ? View.VISIBLE : View.GONE);

        holder.cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ShopCartActivity.checkDelete){
                    if(isChecked){
                        ShopCartActivity.lstShopForDelete.add(lstShoppingCarts.get(position));
                    } else {
                        ShopCartActivity.lstShopForDelete.add(lstShoppingCarts.get(position));
                    }
                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailProductActivity.class);
                intent.putExtra("ProductoEmpresa",lstShoppingCarts.get(position).getProductoEmpresa());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ListViewHolder{
        ImageView ivProduct;
        TextView tvNameProduct;
        TextView tvPriceProduct;
        CheckBox cbDelete;
        RelativeLayout rlDescuento;
        TextView tvDescuento;
    }
}
