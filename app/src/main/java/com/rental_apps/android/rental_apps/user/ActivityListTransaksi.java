package com.rental_apps.android.rental_apps.user;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.adapter.CarsAdapter;
import com.rental_apps.android.rental_apps.adapter.CartAdapter;
import com.rental_apps.android.rental_apps.adapter.Carts;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.model.model_transaksi.ResponseTransaksi;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.move;

import cn.pedant.SweetAlert.SweetAlertDialog;
import customfonts.MyTextView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muhajir on 08/10/2017.
 */

public class ActivityListTransaksi extends AppCompatActivity implements InitComponent, View.OnClickListener {
    private MyTextView checkout;
    private Context mContext;
    private RecyclerView recyclerCart;
    //Declare Adapter
    private CartAdapter mAdapter;
    private SweetAlertDialog pDialog;
    @Override
    protected void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_list_cart);
        mContext=this;
        startInit();
    }

    @Override
    public void startInit() {
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void initUI() {
        recyclerCart = (RecyclerView)findViewById(R.id.rCartList);
        checkout = (MyTextView)findViewById(R.id.checkout);
    }

    @Override
    public void initValue() {
        prepareCart();
        mAdapter.notifyDataSetChanged();
        setCheckout();
    }

    public void setCheckout(){
        checkout.setText("(Rp. "+String.format("%,.2f", Double.parseDouble(Carts.totalAmount(SPref.getCARTS()).toString()))+") Checkout");
    }
    @Override
    public void initEvent() {
        checkout.setOnClickListener(this);
    }

    private void prepareCart(){
        mAdapter = new CartAdapter(mContext,Carts.getOrder(SPref.getCARTS()));
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerCart.setItemAnimator(new DefaultItemAnimator());
        recyclerCart.setAdapter(mAdapter);
        recyclerCart.setItemAnimator(new SlideLeftAlphaAnimator());
        recyclerCart.getItemAnimator().setAddDuration(500);
        recyclerCart.getItemAnimator().setRemoveDuration(500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkout(){
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        Call<ResponseTransaksi> checkout= client.getApi().checkout(Prefs.getString(SPref.getIdUser(),null),""+Carts.totalAmount(SPref.getCARTS()));
        checkout.enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Info?")
                                .setContentText("Data berhasil disimpan!")
                                .setConfirmText("Yes!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        move.moveActivity(mContext, UserMain.class);
                                        finish();
                                    }
                                })
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.checkout:
                if (Carts.getSize(SPref.getCARTS())>0)
                    checkout();
                else
                    Toasty.error(mContext,"Tidak ada pesanan yang diproses",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}