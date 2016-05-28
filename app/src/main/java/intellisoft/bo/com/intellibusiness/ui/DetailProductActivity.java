package intellisoft.bo.com.intellibusiness.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import intellisoft.bo.com.intellibusiness.R;

/**
 * Created by Subieta on 16/05/2016.
 */
public class DetailProductActivity extends AppCompatActivity implements
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout demoSliderProduct;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        demoSliderProduct = (SliderLayout) findViewById(R.id.sldImageProduct);

        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView.description("Descripcion1")
                        .image("http://klipd.com/screenshots/4a6741181059c3548e464f32a3adf67c-1.jpg")
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);
        demoSliderProduct.addSlider(textSliderView);

        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2.description("Descripcion1")
                .image("http://horrorsandscaryshits.blox.pl/resource/hostel4.jpg")
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);
        demoSliderProduct.addSlider(textSliderView2);

        demoSliderProduct.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        demoSliderProduct.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        demoSliderProduct.setCustomAnimation(new DescriptionAnimation());
        demoSliderProduct.setDuration(4000);
        demoSliderProduct.addOnPageChangeListener(this);
        initComponents();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initComponents(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_detail);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.activity_detail_product_tittle));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
