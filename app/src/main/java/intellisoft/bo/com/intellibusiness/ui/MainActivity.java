package intellisoft.bo.com.intellibusiness.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.NewersAdapter;
import intellisoft.bo.com.intellibusiness.components.gridviews.HeaderGridView;
import intellisoft.bo.com.intellibusiness.entity.inventario.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNews;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNews;
import intellisoft.bo.com.intellibusiness.tasks.TaskRegisterGcm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCompleteDownloadNews {

    private intellisoft.bo.com.intellibusiness.components.gridviews.HeaderGridView gridViewNews;
    private List<ProductoEmpresa> lstProductoEmpresas;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        new TaskRegisterGcm(MainActivity.this).execute();
        new TaskDownloadNews(MainActivity.this,this,swipeRefreshLayout).execute();


    }

    private void initComponents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_main);
        refreshSwipe();
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        this.gridViewNews = (HeaderGridView) findViewById(R.id.hgvNews);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void refreshSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                new TaskDownloadNews(MainActivity.this,MainActivity.this,swipeRefreshLayout).execute();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Salir")
                    .setMessage("¿Está seguro que desea salir de IntelliBusiness?")
                    .setNegativeButton(android.R.string.cancel, null)//sin listener
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        public void onClick(DialogInterface dialog, int which) {
                           finish();
                        }
                    }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_openCart:
                finish();
                startActivity(new Intent(MainActivity.this,ShopCartActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCorrectDownload(List<ProductoEmpresa> lstProductoEmpresas) {
        this.lstProductoEmpresas = lstProductoEmpresas;
        this.gridViewNews.setAdapter(new NewersAdapter(MainActivity.this,lstProductoEmpresas));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorDownload() {
        this.lstProductoEmpresas = null;
        swipeRefreshLayout.setRefreshing(false);
    }
}
