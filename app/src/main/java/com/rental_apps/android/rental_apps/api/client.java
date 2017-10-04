package com.rental_apps.android.rental_apps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhajir on 03/09/2017.
 */
public class client {
    private  static  final  String BASE_URL="http://192.168.100.8/rental-api/";
    private  static  final  String BASE_URL_IMAGE="http://192.168.100.8/rental-api/upload/avatars/";

    public static request getApi() {
        //Builder Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        request apiService = retrofit.create(request.class);

        return apiService;
    }

    public static String getBaseUrlImage() {
        return BASE_URL_IMAGE;
    }
}
