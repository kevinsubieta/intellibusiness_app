package intellisoft.bo.com.intellibusiness.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.ShoppingCartAdapter;
import intellisoft.bo.com.intellibusiness.dialogs.LoadingDialog;
import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.ven.CarritoProducto;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadCart;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadProd;
import intellisoft.bo.com.intellibusiness.tasks.TaskDeleteCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadProduct;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShopCartActivity extends AppCompatActivity implements OnCompleteDownloadCart {

    private SwipeRefreshLayout swipe_container_shopcart;
    private List<CarritoProducto> lstShoppingCarts;
    public static List<CarritoProducto> lstShopForDelete = new ArrayList<>();
    private ListView lvShoppingCart;

    private Menu menuShopCart;
    private MenuItem ibActionDeleted;
    private ShoppingCartAdapter shopCartAdapter;
    public static boolean checkDelete;
    private ProgressBar pbLoadingShopCart;
    private TextView tvTittleError;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkDelete = false;
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_shoppingcart);
        initComponents();
        new TaskDownloadCart(ShopCartActivity.this,this,swipe_container_shopcart).execute();
    }

    private void initComponents() {
        this.lvShoppingCart = (ListView) findViewById(R.id.lvShoppingCart);
        this.swipe_container_shopcart = (SwipeRefreshLayout) findViewById(R.id.swipe_container_shopcart);
        this.lvShoppingCart = (ListView) findViewById(R.id.lvShoppingCart);
        this.pbLoadingShopCart = (ProgressBar) findViewById(R.id.pbLoadingShopCart);
        this.tvTittleError = (TextView) findViewById(R.id.tvTittleError);
        this.loadingDialog = new LoadingDialog(ShopCartActivity.this);
        refreshSwipe();

        pbLoadingShopCart.setVisibility(View.VISIBLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.activity_shopcart_tittle));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void refreshSwipe() {
        swipe_container_shopcart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_container_shopcart.setRefreshing(false);
                new TaskDownloadCart(ShopCartActivity.this,ShopCartActivity.this,swipe_container_shopcart).execute();
            }
        });
    }

    private void changeActionBar(){
        if(lstShoppingCarts!=null && lstShoppingCarts.size()>0){
            checkDelete = !checkDelete;
            ibActionDeleted = menuShopCart.findItem(R.id.action_delete_item);
            ibActionDeleted.setVisible(checkDelete ? true : false);
            shopCartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopcart, menu);
        menuShopCart = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete_sweep:
                changeActionBar();
                break;
            case R.id.action_delete_item:
                if(!lstShopForDelete.isEmpty()){
                    new TaskDeleteCart(ShopCartActivity.this,this).execute();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCorrectDownload(List<CarritoProducto> lstShoppingCarts) {
        pbLoadingShopCart.setVisibility(View.GONE);
        tvTittleError.setVisibility(View.GONE);
        this.lstShoppingCarts = lstShoppingCarts;
        this.shopCartAdapter = new ShoppingCartAdapter(ShopCartActivity.this,lstShoppingCarts);
        this.lvShoppingCart.setAdapter(shopCartAdapter);
    }

    @Override
    public void onCorrectDeleted(List<CarritoProducto> lstShoppingDel) {
        ArrayList<CarritoProducto> lstAux = new ArrayList<>(lstShoppingCarts);
        for(CarritoProducto cart : lstShoppingCarts){
            for(CarritoProducto cart1 : lstShoppingDel){
                if(cart.getCliente()==cart1.getCliente() && cart.getProducto()==cart1.getProducto()){
                    lstAux.remove(cart);
                }
            }
        }
        lstShoppingCarts = lstAux;
        changeActionBar();
        shopCartAdapter = new ShoppingCartAdapter(ShopCartActivity.this,lstShoppingCarts);
        this.lvShoppingCart.setAdapter(shopCartAdapter);
        lstShopForDelete.clear();

    }

    @Override
    public void onErrorDownload(int type) {
        switch (type){
            case 1:
                pbLoadingShopCart.setVisibility(View.GONE);
                tvTittleError.setVisibility(View.VISIBLE);
                break;
            case 2:

                break;
        }
    }

}
