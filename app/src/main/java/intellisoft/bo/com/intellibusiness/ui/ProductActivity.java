package intellisoft.bo.com.intellibusiness.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.ProductoAdapter;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by Subieta on 09/04/2016.
 */
public class ProductActivity extends AppCompatActivity {

    private List<ProductoEmpresa> lstProductoEmpresa;
    private ListView lvProducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        initComponents();
        loadProducts();
    }

    private void initComponents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.lvProducts = (ListView) findViewById(R.id.lvProducts);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductActivity.this, InboxActivity.class));
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.activity_product_tittle));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }

    }

    private void loadProducts(){
        this.lstProductoEmpresa= (List<ProductoEmpresa>) getIntent().getExtras().getSerializable("lstProductosBuscados");
        ProductoAdapter productoAdapter = new ProductoAdapter(ProductActivity.this,lstProductoEmpresa);
        lvProducts.setAdapter(productoAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_producto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                return true;
            case R.id.action_openCart:
                finish();
                startActivity(new Intent(ProductActivity.this,ShopCartActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
