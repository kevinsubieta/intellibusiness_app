package intellisoft.bo.com.intellibusiness.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.paypal.android.sdk.ca;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import intellisoft.bo.com.intellibusiness.R;
import intellisoft.bo.com.intellibusiness.entity.inv.ImagenProducto;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEscalar;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoNumerica;
import intellisoft.bo.com.intellibusiness.entity.inv.ValorEscalar;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteAddShop;
import intellisoft.bo.com.intellibusiness.listeners.OnCompleteSaveBuy;
import intellisoft.bo.com.intellibusiness.tasks.TaskAddShopCart;
import intellisoft.bo.com.intellibusiness.tasks.TaskSaveBuy;
import intellisoft.bo.com.intellibusiness.utils.AppStatics;

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
    private TextView tvState;
    private EditText etQuantity;
    private RelativeLayout rlDescuento;
    private TextView tvDescuento;
    private TextView tvPriceAnt;



    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
    private boolean descuento = false;
    private BigDecimal precioDescuento;

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
        tvState = (TextView) findViewById(R.id.tvState);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        rlDescuento = (RelativeLayout) findViewById(R.id.rlDescuento);
        tvDescuento = (TextView) findViewById(R.id.tvDesc);
        tvPriceAnt = (TextView) findViewById(R.id.tvPriceAnt);
        tvPriceAnt.setPaintFlags(tvPriceAnt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        productoEmpresa = (ProductoEmpresa) getIntent().getExtras().getSerializable("ProductoEmpresa");
        demoSliderProduct = (SliderLayout) findViewById(R.id.sldImageProduct);
        List<ProductoEscalar> lstProductoEscalar=null;
        List<ProductoNumerica> lstProductoNumerica=null;


        if(productoEmpresa!=null){
            if(productoEmpresa.getInsEmpresa()!=null) {
                if (productoEmpresa.getInsEmpresa().getLogo() != null) {
                    (new AQuery(DetailProductActivity.this)).id(ivCompanyDet).image(productoEmpresa.getInsEmpresa().getLogo());
                }
                tvCompanyDet.setText(productoEmpresa.getInsEmpresa().getNombre());
            }
            if(productoEmpresa.getInsProducto()!=null){
                lstProductoEscalar = productoEmpresa.getInsProducto().getLstProductoEscalar();
                lstProductoNumerica = productoEmpresa.getInsProducto().getLstProductoNumerica();
            }

            if(productoEmpresa.getLstProductoDes()!=null &&
                    productoEmpresa.getLstProductoDes().size() >0 ){
                descuento = true;
               rlDescuento.setVisibility(View.VISIBLE);
                int descuento = productoEmpresa.getLstProductoDes().get(productoEmpresa.getLstProductoDes().size()-1).
                                                                                    getInsDescuento().getPorcentaje();
                tvDescuento.setText("-"+descuento+"%");
                tvProductTittle.setText(productoEmpresa.getNombre());
                BigDecimal valorDescontar = (productoEmpresa.getPrecio().multiply(new BigDecimal(descuento)))
                                                                        .divide(new BigDecimal(100));
                String newValor = productoEmpresa.getPrecio().subtract(valorDescontar).toString();
                precioDescuento = new BigDecimal(newValor);
                tvProductPrice.setText(precioDescuento+" $u$");
                tvPriceAnt.setVisibility(View.VISIBLE);
                tvPriceAnt.setText(productoEmpresa.getPrecio().toString()+" $u$");


            } else {
                tvProductTittle.setText(productoEmpresa.getNombre());
                tvProductPrice.setText(productoEmpresa.getPrecio().toString()+" $u$");
            }

        }

        String description = "";
        if(lstProductoEscalar!= null && !lstProductoEscalar.isEmpty()){
            for(ProductoEscalar productoEscalar : lstProductoEscalar){
                if(productoEscalar.getInsValorEscalar()!=null){
                    for(ValorEscalar valorEscalar : productoEscalar.getInsValorEscalar()){
                        if(valorEscalar.getInsEscalar()!=null){
                            description += valorEscalar.getInsEscalar().getNombre()+" : "+ valorEscalar.getValor()+"\n";
                        }
                    }
                }
            }
        }

        if(lstProductoNumerica!=null && !lstProductoNumerica.isEmpty()){
            for(ProductoNumerica productoNumerica : lstProductoNumerica){
                if(productoNumerica.getInsNumerica()!=null){
                    description += productoNumerica.getInsNumerica().getNombre()+" : " + productoNumerica.getValor()+"\n";
                }
            }
        }
        tvState.setText(description);

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
        int cantidad = 1;
        if(etQuantity.getText().toString().equals("") || etQuantity.getText().toString().equals("0") || etQuantity.getText().equals(" ")){
            cantidad = 1;
        }else{
           cantidad = Integer.parseInt(etQuantity.getText().toString());
        }

        BigDecimal price;
        if(descuento){
            price = precioDescuento;
        } else {
            price = productoEmpresa.getPrecio();
        }
        price = price.multiply(new BigDecimal(cantidad));

        return new PayPalPayment(price ,
                "USD", productoEmpresa.getNombre()+" X"+Integer.toString(cantidad),
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
        if(requestCode == REQUEST_CODE_PAYMENT){
            if(resultCode == RESULT_OK){
                BigDecimal price;
                if(descuento){
                    price = precioDescuento;
                } else {
                    price = productoEmpresa.getPrecio();
                }

                new TaskSaveBuy(DetailProductActivity.this,this,productoEmpresa,Integer.parseInt(etQuantity.getText().toString()),price).execute();
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
