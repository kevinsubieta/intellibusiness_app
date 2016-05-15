package intellisoft.bo.com.intellibusiness.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.components.adapters.InboxAdapter;
import intellisoft.bo.com.intellibusiness.entity.app.Notifications;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteDownloadNotif;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskDownloadNotif;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;

/**
 * Created by Subieta on 14/05/2016.
 */
public class InboxActivity extends AppCompatActivity implements OnCompleteDownloadNotif{
    private ListView lvInbox;
    private SwipeRefreshLayout swipe_container_inbox;
    private List<Notifications> lstNotifications;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        initComponents();
        new TaskDownloadNotif(InboxActivity.this,InboxActivity.this,swipe_container_inbox).execute();
    }

    private void initComponents(){
        this.lvInbox = (ListView) findViewById(R.id.lvInbox);
        this.swipe_container_inbox = (SwipeRefreshLayout) findViewById(R.id.swipe_container_inbox);
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

    private void refreshSwipe() {
        swipe_container_inbox.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_container_inbox.setRefreshing(false);
                new TaskDownloadNotif(InboxActivity.this,InboxActivity.this,swipe_container_inbox).execute();
            }
        });
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
    public void onCorrectDownload(List<Notifications> lstNotifications) {
        this.lstNotifications = lstNotifications;
        this.lvInbox.setAdapter(new InboxAdapter(InboxActivity.this,lstNotifications));
    }

    @Override
    public void onErrorDownload() {

    }
}
