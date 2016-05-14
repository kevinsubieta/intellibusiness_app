package intellisoft.bo.com.intellibusiness.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNews;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShopCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;
    private SwipeRefreshLayout swipe_container_shopcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        initComponents();
    }

    private void initComponents(){
        this.lvShoppingCart = (ListView) findViewById(R.id.lvShoppingCart);
        this.swipe_container_shopcart = (SwipeRefreshLayout) findViewById(R.id.swipe_container_shopcart);
        refreshSwipe();
    }

    private void refreshSwipe() {
        swipe_container_shopcart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_container_shopcart.setRefreshing(false);
                //Tarea ---
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
