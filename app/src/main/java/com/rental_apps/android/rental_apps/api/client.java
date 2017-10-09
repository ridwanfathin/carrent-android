package com.rental_apps.android.rental_apps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhajir on 03/09/2017.
 */
public class client {
    private  static  final  String BASE_URL="http://10.10.67.232/rental-api/";
    private  static  final  String BASE_URL_IMAGE="http://10.10.67.232/rental-api/upload/avatars/";
    private  static  final  String BASE_URL_IMG="http://10.10.67.232/rental-api/upload/";

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

    public static String getBaseImg() {
        return BASE_URL_IMG;
    }

}
