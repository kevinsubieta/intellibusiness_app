package intellisoft.bo.com.intellibusiness.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.InboxAdapter;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;
import intellisoft.bo.com.intellibusiness.entity.mark.Inbox;
import intellisoft.bo.com.intellibusiness.entity.mark.Notificacion;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNotif;
import intellisoft.bo.com.intellibusiness.tasks.TaskDeleteNotif;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNotif;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;

/**
 * Created by Subieta on 14/05/2016.
 */
public class InboxActivity extends AppCompatActivity implements OnCompleteDownloadNotif{
    private ListView lvInbox;
    private InboxAdapter inboxAdapter;
    private SwipeRefreshLayout swipe_container_inbox;
    private List<Inbox> lstInbox;
    public static List<Inbox> lstInboxForDelete = new ArrayList<>();
    public static boolean checkDelete;
    private MenuItem ibActionDeleted;
    private Menu menuItems;
    private CheckBox cbItems;
    private ProgressBar pbLoadingInbox;
    private TextView tvTittleError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkDelete = false;
        setContentView(R.layout.activity_inbox);
        initComponents();
        new TaskDownloadNotif(InboxActivity.this,InboxActivity.this,swipe_container_inbox).execute();
    }

    private void initComponents(){
        this.lvInbox = (ListView) findViewById(R.id.lvInbox);
        this.swipe_container_inbox = (SwipeRefreshLayout) findViewById(R.id.swipe_container_inbox);
        this.cbItems = (CheckBox) findViewById(R.id.cbDelete);
        this.pbLoadingInbox = (ProgressBar) findViewById(R.id.pbLoadingInbox);
        this.pbLoadingInbox.setVisibility(View.VISIBLE);
        this.tvTittleError = (TextView) findViewById(R.id.tvTittleError);
        refreshSwipe();


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.activity_inbox_tittle));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    private void refreshSwipe() {
        swipe_container_inbox.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_container_inbox.setRefreshing(false);
                new TaskDownloadNotif(InboxActivity.this,InboxActivity.this,swipe_container_inbox).execute();
            }
        });
    }

    public void onClicItemCheckBox(View view){
        if(cbItems.isChecked()){


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
        this.menuItems = menu;
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
                if(!lstInboxForDelete.isEmpty()){
                    new TaskDeleteNotif(InboxActivity.this,this).execute();
                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void changeActionBar(){
        if(lstInbox !=null && lstInbox.size()>0){
            checkDelete = !checkDelete;
            ibActionDeleted = menuItems.findItem(R.id.action_delete_item);
            ibActionDeleted.setVisible(checkDelete ? true : false);
            inboxAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCorrectDownload(List<Inbox> lstNotifications) {
        this.lstInbox = lstNotifications;
        this.pbLoadingInbox.setVisibility(View.GONE);
        inboxAdapter = new InboxAdapter(InboxActivity.this,lstInbox);
        this.lvInbox.setAdapter(inboxAdapter);
    }

    @Override
    public void onErrorDownload(int type) {
        switch (type){
        case 1:
            this.pbLoadingInbox.setVisibility(View.GONE);
            this.tvTittleError.setVisibility(View.VISIBLE);
        break;
            case 2:

                break;
        }
    }

    @Override
    public void onCorrectDelete(List<Inbox> lstDeletedInbox) {
        ArrayList<Inbox> lstAux = new ArrayList<>(lstInbox);
        for(Inbox inbox : lstInbox){
            for(Inbox inbox1 : lstDeletedInbox){
                if(inbox.getIdc() == inbox1.getIdc() && inbox.getIdn()==inbox1.getIdn()){
                    lstAux.remove(inbox);
                }
            }
        }
        lstInbox = lstAux;
        changeActionBar();
        inboxAdapter = new InboxAdapter(InboxActivity.this,lstInbox);
        this.lvInbox.setAdapter(inboxAdapter);
        lstInboxForDelete.clear();
    }
}
