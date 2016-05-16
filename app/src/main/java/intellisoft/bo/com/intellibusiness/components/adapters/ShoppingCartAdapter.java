package intellisoft.bo.com.intellibusiness.components.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.ui.ShopCartActivity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    Context context;
    List<ShoppingCart> lstShoppingCarts;
    AQuery aQuery;

    public ShoppingCartAdapter(Context context, List<ShoppingCart> lstShoppingCarts) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.view_item_shopcart,null,true);
        ListViewHolder holder = new ListViewHolder();
        holder.ivProduct = (ImageView) convertView.findViewById(R.id.ivProduct);
        holder.tvNameProduct = (TextView) convertView.findViewById(R.id.tvNameProduct);
        holder.tvPriceProduct = (TextView) convertView.findViewById(R.id.tvPriceProduct);
        holder.cbDelete = (CheckBox) convertView.findViewById(R.id.cbDelete);

        aQuery.id(holder.ivProduct).image(lstShoppingCarts.get(position).getImage());
        holder.tvNameProduct.setText(lstShoppingCarts.get(position).getNombre());
        holder.tvPriceProduct.setText(Double.toString(lstShoppingCarts.get(position).getPrecio()) + " $u$");
        holder.cbDelete.setVisibility(ShopCartActivity.checkDelete ? View.VISIBLE : View.GONE);
        return convertView;
    }

    static class ListViewHolder{
        ImageView ivProduct;
        TextView tvNameProduct;
        TextView tvPriceProduct;
        CheckBox cbDelete;
    }
}
