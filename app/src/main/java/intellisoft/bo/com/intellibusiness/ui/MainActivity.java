package intellisoft.bo.com.intellibusiness.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.NewersAdapter;
import intellisoft.bo.com.intellibusiness.components.gridviews.HeaderGridView;
import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.inv.Suggestion;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNews;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNews;
import intellisoft.bo.com.intellibusiness.tasks.TaskRegisterGcm;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;
import intellisoft.bo.com.intellibusiness.utils.PreferencesManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCompleteDownloadNews {

    private intellisoft.bo.com.intellibusiness.components.gridviews.HeaderGridView gridViewNews;
    private List<ProductoEmpresa> lstProductoEmpresas;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressbar;
    private String TAG = "MainActivity";
    private PreferencesManager preferencesManager;
    private Usuario usuario;
    private SimpleCursorAdapter busStopCursorAdapter;
    private SearchView searchView;
    private TextView tvTittleError;

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
        refreshSwipe();
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        this.progressbar = (ProgressBar) findViewById(R.id.pbLoadingNewers);
        this.progressbar.setVisibility(View.VISIBLE);
        this.gridViewNews = (HeaderGridView) findViewById(R.id.hgvNews);
        this.tvTittleError = (TextView) findViewById(R.id.tvTittleError);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InboxActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        preferencesManager = new PreferencesManager(MainActivity.this);
        usuario = preferencesManager.getUsuario();
        if(usuario!= null){
            ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tvNavUserName)).setText(usuario.getLoggin());
            ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tvNavEmail)).setText(usuario.getEmail());
            new AQuery(MainActivity.this).id((ImageView)navigationView.getHeaderView(0).
                    findViewById(R.id.ivNavUserPicture)).image(R.drawable.ic_account_circle);
        }

    }



    private void refreshSwipe() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_main);
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
                            Log.d(TAG, getResources().getString(R.string.splash_activity_msjcancel));
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        makeSercherProducts(searchView);
        return true;
    }

    public void makeSercherProducts(final SearchView searchView){

        List<Suggestion> suggestionsList = loadSuggestions(searchView.getQuery().toString());
        String[] columnNames = {"_id","nombre","precio"};
        MatrixCursor cursor = new MatrixCursor(columnNames);
        String[] temp = new String[3];
        if(suggestionsList != null){
            for(Suggestion suggestion : suggestionsList){
                temp[0] = Integer.toString(suggestion.getId());
                temp[1] = suggestion.getNombre();
                temp[2] = suggestion.getPrice() + AppStatics.MONEDA;
                cursor.addRow(temp);
            }
        }else{
            temp[0] = "0";
            temp[1] = "No se pudieron cargar datos de la busqueda";
            temp[2] = "0.0 Bs";
        }

        String[] from = {"nombre","precio"};
        int[] to = {R.id.tvTittleSuggestion,R.id.tvPriceSuggestion};
        busStopCursorAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.view_item_suggestion, cursor, from, to,0);
        searchView.setSuggestionsAdapter(busStopCursorAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {

            @Override
            public boolean onSuggestionClick(int position) {
//                String selectedItem = (String)busStopCursorAdapter.getItem(position);
                Toast.makeText(MainActivity.this,"onSuggestionClick",Toast.LENGTH_SHORT).show();
       //         Log.v("search view", selectedItem);
                return false;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                Toast.makeText(MainActivity.this,"onSuggestionSelect",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                makeSercherProducts(searchView);
                return true;
            }
        });
    }

    private List<Suggestion> loadSuggestions(String texto){
        List<Suggestion> suggestions = new ArrayList<>();
        if(lstProductoEmpresas==null){
            return null;
        }
        for(int i = 0; i<lstProductoEmpresas.size(); i++){
            if(suggestions.size()==3){
                return suggestions;
            }
            ProductoEmpresa productoEmpresa = lstProductoEmpresas.get(i);
            if( productoEmpresa.getNombre().toUpperCase().startsWith(texto.toUpperCase())){
                suggestions.add(new Suggestion(productoEmpresa.getId(),
                        productoEmpresa.getNombre(),productoEmpresa.getPrecio().toString()));
            }
        }
        return suggestions;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_openCart:
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

        } else if (id == R.id.nav_logout) {
            preferencesManager.setUsuario(null);
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK ));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCorrectDownload(List<ProductoEmpresa> lstProductoEmpresas) {
        this.lstProductoEmpresas = lstProductoEmpresas;
        this.progressbar.setVisibility(View.GONE);
        this.tvTittleError.setVisibility(View.GONE);
        this.gridViewNews.setAdapter(new NewersAdapter(MainActivity.this,lstProductoEmpresas));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorDownload() {
        this.lstProductoEmpresas = null;
        this.progressbar.setVisibility(View.GONE);
        this.tvTittleError.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
