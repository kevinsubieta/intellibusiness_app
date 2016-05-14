package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.ShoppingCartAdapter;
import intellisoft.bo.com.intellibusiness.entity.app.ShoppingCart;
import intellisoft.bo.com.intellibusiness.entity.inventario.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNews;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShopCartActivity extends AppCompatActivity implements OnCompleteDownloadCart {

    private SwipeRefreshLayout swipe_container_shopcart;
    private List<ShoppingCart> lstShoppingCarts;
    private ListView lvShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_shoppingcart);
        initComponents();
        new TaskDownloadCart(ShopCartActivity.this,this,swipe_container_shopcart).execute();
    }

    private void initComponents() {
        //   getActionBar().setTitle(getResources().getString(R.string.activity_shopcart_name));
        this.lvShoppingCart = (ListView) findViewById(R.id.lvShoppingCart);
        this.swipe_container_shopcart = (SwipeRefreshLayout) findViewById(R.id.swipe_container_shopcart);
        this.lvShoppingCart = (ListView) findViewById(R.id.lvShoppingCart);
        refreshSwipe();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Hola");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopcart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete_sweep:
                break;
            case R.id.action_delete_item:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCorrectDownload(List<ShoppingCart> lstShoppingCarts) {
        this.lstShoppingCarts = lstShoppingCarts;
        this.lvShoppingCart.setAdapter(new ShoppingCartAdapter(ShopCartActivity.this,lstShoppingCarts));
    }

    @Override
    public void onErrorDownload() {

    }
}
