package intellisoft.bo.com.intellibusiness.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.inv.ImagenProducto;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteAddShop;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSaveBuy;
import intellisoft.bo.com.intellibusiness.tasks.TaskAddShopCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskSaveBuy;

/**
 * Created by Subieta on 16/05/2016.
 */
public class DetailProductActivity extends AppCompatActivity implements
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, OnCompleteAddShop,OnCompleteSaveBuy {

    private SliderLayout demoSliderProduct;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProductoEmpresa productoEmpresa;
    private TextView tvProductTittle;
    private TextView tvProductPrice;
    private ImageView ivCompanyDet;
    private TextView tvCompanyDet;


    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initComponents();
        initSlider();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initSlider(){
        List<ImagenProducto> lstImagenes = productoEmpresa.getLstImgProducto();
        if(lstImagenes.size()>0){
            for(ImagenProducto imagenProducto : lstImagenes){
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView.description(productoEmpresa.getDetalle())
                        .image(imagenProducto.getUrl())
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);
                demoSliderProduct.addSlider(textSliderView);
            }
        } else {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description(getResources().getString(R.string.activity_detail_image_noaval))
                    .image(R.drawable.ic_image_no_aval)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            demoSliderProduct.addSlider(textSliderView);
        }


        demoSliderProduct.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        demoSliderProduct.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        demoSliderProduct.setCustomAnimation(new DescriptionAnimation());
        demoSliderProduct.setDuration(4000);
        demoSliderProduct.addOnPageChangeListener(this);
        demoSliderProduct.stopAutoCycle();
    }


    private void initComponents(){
        tvProductTittle = (TextView) findViewById(R.id.tvProductTittle);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        ivCompanyDet = (ImageView) findViewById(R.id.ivCompanyDet);
        tvCompanyDet = (TextView) findViewById(R.id.tvCompanyDet);

        productoEmpresa = (ProductoEmpresa) getIntent().getExtras().getSerializable("ProductoEmpresa");
        demoSliderProduct = (SliderLayout) findViewById(R.id.sldImageProduct);

        tvProductTittle.setText(productoEmpresa.getNombre());
        tvProductPrice.setText(productoEmpresa.getPrecio().toString()+" Bs");
        initSwipeRefreshLayout();
        initActionBar();
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_detail);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initActionBar(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.activity_detail_product_tittle));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    public void clicBuyProduct(View view){
        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(DetailProductActivity.this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    public void clicAddShopCart(View view){
        new TaskAddShopCart(DetailProductActivity.this,this,productoEmpresa).execute();
    }


    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(productoEmpresa.getPrecio(), "USD", productoEmpresa.getNombre(),
                paymentIntent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_FUTURE_PAYMENT){
            if(resultCode == RESULT_OK){
                new TaskSaveBuy(DetailProductActivity.this,this,productoEmpresa).execute();
            }else{
                this.onErrorSaveBuy();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onCorrectAddCart(Boolean result) {
        Toast.makeText(DetailProductActivity.this,getResources().getString(R.string.activity_detail_product_msjAddShop),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorAddCart() {
        Toast.makeText(DetailProductActivity.this,getResources().getString(R.string.activity_detail_product_msjErrorAddShop),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleteSaveBuy(Boolean result) {
        Toast.makeText(DetailProductActivity.this,getResources().getString(R.string.activity_detail_product_msjOkBuy),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorSaveBuy() {
        Toast.makeText(DetailProductActivity.this,getResources().getString(R.string.activity_detail_product_msjErrorBuy),
                Toast.LENGTH_SHORT).show();
    }
}
