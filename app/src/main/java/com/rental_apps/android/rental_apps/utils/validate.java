package com.rental_apps.android.rental_apps.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Muhajir on 02/09/2017.
 */
public class validate {

    public static boolean cek(EditText et) {
            View focusView = null;
            Boolean cancel=false;
            if (TextUtils.isEmpty(et.getText().toString().trim())) {
                et.setError("Inputan Harus Di Isi");
                focusView = et;
                cancel = true;
            }
            if (cancel) {
                focusView.requestFocus();
            }
            return cancel;
    }

    public static boolean cekPassword(EditText et,String Password,String ConfirmPassword){
        View focusView = null;
        Boolean cancel=false;
        if (!Password.equals(ConfirmPassword)) {
            et.setError("Password tidak sama");
            focusView = et;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        return cancel;
    }
}
