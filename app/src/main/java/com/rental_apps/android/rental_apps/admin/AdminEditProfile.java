package com.rental_apps.android.rental_apps.admin;

/**
 * Created by Muhajir on 07/10/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.helper.Hash;
import com.rental_apps.android.rental_apps.model.model_user.DataUser;
import com.rental_apps.android.rental_apps.model.model_user.ResponseRegister;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.validate;
import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;
import customfonts.MyEditText;
import customfonts.MyTextView;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muhajir on 01/10/2017.
 */

public class AdminEditProfile extends AppCompatActivity implements InitComponent, View.OnClickListener {
    private MyEditText name;
    private MyEditText email;
    private MyEditText noTelp;
    private MyEditText address;
    private MyEditText jenis_kelamin;
    private MyEditText status;
    private MyEditText username;
    private MyEditText old_password;
    private MyEditText new_password;
    private MyEditText confirm_password;

    private CircleImageView userPhoto;

    private Button update;

    private SweetAlertDialog pDialog;

    private String JK;

    Context mContext;
    Toolbar toolbar;
    DataUser user;

    private Boolean ket=false;

    @Override
    protected void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_edit_user);

        Gson gson = new Gson();
        user= gson.fromJson(getIntent().getStringExtra("user"), DataUser.class);

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
        toolbar=(Toolbar)findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_action_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void initUI() {
        name=(MyEditText)findViewById(R.id.name);
        email=(MyEditText)findViewById(R.id.email);
        noTelp=(MyEditText)findViewById(R.id.notelp);
        address=(MyEditText)findViewById(R.id.address);
        jenis_kelamin=(MyEditText)findViewById(R.id.jenis_kelamin);
        status=(MyEditText)findViewById(R.id.status);
        username=(MyEditText)findViewById(R.id.username);
        old_password=(MyEditText)findViewById(R.id.old_password);
        new_password=(MyEditText)findViewById(R.id.password);
        confirm_password=(MyEditText)findViewById(R.id.confirm_password);
        userPhoto=(CircleImageView)findViewById(R.id.userPhoto);
        update=(Button)findViewById(R.id.btn_update);

    }


    @Override
    public void initValue() {

        name.setText(Prefs.getString(SPref.getNAME(),""));
        email.setText(Prefs.getString(SPref.getEMAIL(),""));
        noTelp.setText(Prefs.getString(SPref.getNoTelp(),""));
        address.setText(Prefs.getString(SPref.getALAMAT(),""));
        username.setText(Prefs.getString(SPref.getUSERNAME(),""));
        JK=Prefs.getString(SPref.getJenisKelamin(),"");
        if (Prefs.getString(SPref.getJenisKelamin(),"").equals('L')){
            jenis_kelamin.setText("Laki-laki");
        }else{
            jenis_kelamin.setText("Perempuan");
        }

        status.setText("Aktif");

        if(!Prefs.getString(SPref.getPHOTO(),null).isEmpty())
            Picasso.with(mContext).load(client.getBaseUrlImage()+Prefs.getString(SPref.getPHOTO(),null)).into(userPhoto);


    }

    @Override
    public void initEvent() {
        old_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!Hash.MD5(old_password.getText().toString()).equals(Prefs.getString(SPref.getPASSWORD(),""))){
                    View focusView = null;
                    old_password.setError("Password tidak sama");
                    focusView = old_password;
                    focusView.requestFocus();
                    ket=false;
                }else{
                    ket=true;
                }
            }
        });

        update.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                if(validasi())
                    register();
                break;
            case R.id.jkl:
                JK="L";
//                rbp.setChecked(false);
                break;
            case R.id.jkp:
                JK="P";
//                rbl.setChecked(false);
                break;
            case R.id.btn_update:
                if(validasi())
                    register();
                break;
        }
    }

    private void register(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        Call<ResponseRegister> register;
//        Toasty.success(mContext,Prefs.getString(SPref.getIdUser(),""),Toast.LENGTH_SHORT).show();

        register = client.getApi().userUpdate(""+Prefs.getInt(SPref.getIdUser(),0),name.getText().toString(),
                username.getText().toString(),
                email.getText().toString(),
                noTelp.getText().toString(),
                JK,
                address.getText().toString(),
                new_password.getText().toString(),
                1,1);

        register.enqueue(new Callback<ResponseRegister>() {

            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                pDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getStatus()) {
                        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Info")
                                .setContentText("Akun Berhasil Di Update!")
                                .show();
                    }else {
                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Info")
                                .setContentText("Akun Gagal Di Update!")
                                .show();
                    }
                }else{
                    new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Info")
                            .setContentText("Akun Gagal Di Update!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                pDialog.hide();
                new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Koneksi bermasalah!")
                        .show();
            }
        });
    }

    private Boolean validasi(){
        if (!validate.cek(name)
                &&!validate.cek(username)
                &&!validate.cek(email)
                &&!validate.cek(noTelp)
                &&!validate.cek(address)
                &&!validate.cek(old_password)&&ket) {
            if (validate.cekPassword(confirm_password,new_password.getText().toString(),confirm_password.getText().toString())){
                return false;
            }else{
                return true;
            }
        } else{ return false; }
    }
}

