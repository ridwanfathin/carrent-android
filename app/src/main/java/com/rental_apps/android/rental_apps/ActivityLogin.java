package com.rental_apps.android.rental_apps;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.admin.AdminMain;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.model.model_user.DataUser;
import com.rental_apps.android.rental_apps.model.model_user.ResponseLogin;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.user.UserMain;
import com.rental_apps.android.rental_apps.utils.move;
import com.rental_apps.android.rental_apps.utils.validate;

import cn.pedant.SweetAlert.SweetAlertDialog;
import customfonts.MyEditText;
import customfonts.MyTextView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity implements InitComponent, View.OnClickListener {

    //declare componenr
    private MyEditText et_email;
    private MyEditText et_password;
    private MyTextView btn_login;
    private MyTextView txt_register;
    private TextView logofont;
    private CoordinatorLayout coordinatorlayout;

    //declare context
    private Context mContext;

    //declate variable
    private DataUser userData;

    //declare sweet alert
    private SweetAlertDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=this;
        startInit();
    }

    @Override
    public void startInit() {
        if (Prefs.getInt(SPref.getGroupUser(),0)==1){
            move.moveActivity(mContext,AdminMain.class);
            finish();
        }
        if (Prefs.getInt(SPref.getGroupUser(),0)==2){
            move.moveActivity(mContext,UserMain.class);
            finish();
        }
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar() {
        getSupportActionBar().hide();
    }

    @Override
    public void initUI() {
        et_email=(MyEditText)findViewById(R.id.et_email);
        et_password=(MyEditText)findViewById(R.id.et_password);
        btn_login=(MyTextView)findViewById(R.id.btn_login);
        txt_register=(MyTextView) findViewById(R.id.txt_register);
        logofont=(TextView)findViewById(R.id.logofont);
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        logofont.setTypeface(custom_fonts);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        btn_login.setOnClickListener(this);
        txt_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_login:
//                if (validate_login())
                    login();
                break;

            case R.id.txt_register:
                move.moveActivity(mContext,ActivityRegister.class);
                break;
        }
    }

    public boolean validate_login(){
        return (!validate.cek(et_email)&&!validate.cek(et_password)) ? true : false;
    }

    public void login(){
//        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();

        Call<ResponseLogin> user=client.getApi().auth(et_email.getText().toString(),et_password.getText().toString());
        Log.d("data user", et_email.getText().toString() + et_password.getText().toString());
//        Log.d("data user", String.valueOf(user));
        user.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
//                pDialog.hide();
//                if (response.isSuccessful()){
                if (true){
//                    if (response.body().getStatus()){
                    if (true){
//                        userData=response.body().getData();
//                        Toasty.success(mContext,"login berhasil",Toast.LENGTH_LONG).show();
//                        Log.d("data user",userData.toString());
//                        setPreference(userData);
//                        if (userData.getGroup_user().equals(1))
//                            move.moveActivity(mContext,AdminMain.class);
//                        else
                            move.moveActivity(mContext,UserMain.class);
                        finish();
                    }else{
                        Toasty.error(mContext,"Username dan password salah",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"Username dan password salah2",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
//                pDialog.hide();
//                new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("Oops...")
//                        .setContentText("Koneksi bermasalah!")
//                        .show();
                Toasty.error(mContext,"Koneksi bermasalah!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setPreference(DataUser du){
        Prefs.putInt(SPref.getIdUser(),du.getId_user());
        Prefs.putString(SPref.getUSERNAME(),du.getUsername());
        Prefs.putString(SPref.getNAME(),du.getName());
        Prefs.putString(SPref.getEMAIL(),du.getEmail());
        Prefs.putString(SPref.getNoTelp(),du.getNo_telp());
        Prefs.putString(SPref.getJenisKelamin(),du.getJenis_kelamin().toString());
        Prefs.putString(SPref.getPHOTO(),du.getPhoto());
        Prefs.putString(SPref.getLastUpdate(),du.getLast_update().toString());
        Prefs.putString(SPref.getALAMAT(),du.getAlamat());
        Prefs.putInt(SPref.getGroupUser(),du.getGroup_user());
        Prefs.putString(SPref.getPASSWORD(),du.getPassword().toString());
    }


}
