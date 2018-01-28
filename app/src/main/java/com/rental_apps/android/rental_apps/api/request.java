package com.rental_apps.android.rental_apps.api;

import com.rental_apps.android.rental_apps.model.model_dashboard.ResponseInfoDashboard;
import com.rental_apps.android.rental_apps.model.model_detail_transaksi.ResponseDetailTransaksi;
import com.rental_apps.android.rental_apps.model.model_history.ResponseHistory;
import com.rental_apps.android.rental_apps.model.model_mobil.ResponseRegisterCars;
import com.rental_apps.android.rental_apps.model.model_transaksi.ResponseRegisterTransaksi;
import com.rental_apps.android.rental_apps.model.model_transaksi.ResponseTransaksi;
import com.rental_apps.android.rental_apps.model.model_user.ResponseLogin;
import com.rental_apps.android.rental_apps.model.model_mobil.ResponseCars;
import com.rental_apps.android.rental_apps.model.model_user.ResponseRegister;
import com.rental_apps.android.rental_apps.model.model_user.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */
public interface request{
    @FormUrlEncoded
    @POST("api/auth")
    Call<ResponseLogin> auth(@Field("EMAIL") String email,
                             @Field("PASSWORD") String password);

    @FormUrlEncoded
    @POST("api/user")
    Call<ResponseRegister> userRegister(@Field("NAME")String name,
                                        @Field("USERNAME")String username,
                                        @Field("EMAIL")String email,
                                        @Field("NO_TELP")String no_telp,
                                        @Field("JENIS_KELAMIN")Character jenis_kelamin,
                                        @Field("ALAMAT")String alamat,
                                        @Field("PASSWORD")String password,
                                        @Field("ACTIVATED")Integer activated,
                                        @Field("GROUP_USER")Integer group_user);
    @FormUrlEncoded
    @PUT("api/user")
    Call<ResponseRegister> userUpdate(@Field("ID_USER")String ID_USER,
                                      @Field("NAME")String name,
                                      @Field("USERNAME")String username,
                                      @Field("EMAIL")String email,
                                      @Field("NO_TELP")String no_telp,
                                      @Field("JENIS_KELAMIN")String jenis_kelamin,
                                      @Field("ALAMAT")String alamat,
                                      @Field("PASSWORD")String password,
                                      @Field("ACTIVATED")Integer activated,
                                      @Field("GROUP_USER")Integer group_user,
                                      @Field("PHOTO")String photo);


    @GET("api/mobil")
    Call<ResponseCars>  dataMobil();


    @FormUrlEncoded
    @POST("api/mobil")
    Call<ResponseRegisterCars> mobilRegister(@Field("NAMA_MOBIL") String NAMA_MOBIL,
                                             @Field("MERK_MOBIL") String MERK_MOBIL,
                                             @Field("DESKRIPSI_MOBIL") String DESKRIPSI_MOBIL,
                                             @Field("TAHUN_MOBIL") String TAHUN_MOBIL,
                                             @Field("KAPASITAS_MOBIL") String KAPASITAS_MOBIL,
                                             @Field("HARGA_MOBIL") String HARGA_MOBIL,
                                             @Field("WARNA_MOBIL") String WARNA_MOBIL,
                                             @Field("BENSIN_MOBIL") Integer BENSIN_MOBIL,
                                             @Field("PLAT_NO_MOBIL") String PLAT_NO_MOBIL,
                                             @Field("STATUS_MOBIL") String STATUS_MOBIL,
                                             @Field("PHOTO") String PHOTO
                                             );

    @FormUrlEncoded
    @PUT("api/mobil")
    Call<ResponseRegisterCars> mobilUpdate(@Field("ID_MOBIL") String ID_MOBIL,
                                           @Field("NAMA_MOBIL") String NAMA_MOBIL,
                                             @Field("MERK_MOBIL") String MERK_MOBIL,
                                             @Field("DESKRIPSI_MOBIL") String DESKRIPSI_MOBIL,
                                             @Field("TAHUN_MOBIL") String TAHUN_MOBIL,
                                             @Field("KAPASITAS_MOBIL") String KAPASITAS_MOBIL,
                                             @Field("HARGA_MOBIL") String HARGA_MOBIL,
                                             @Field("WARNA_MOBIL") String WARNA_MOBIL,
                                             @Field("BENSIN_MOBIL") Integer BENSIN_MOBIL,
                                             @Field("PLAT_NO_MOBIL") String PLAT_NO_MOBIL,
                                             @Field("STATUS_MOBIL") String STATUS_MOBIL,
                                             @Field("PHOTO") String PHOTO
    );


    @GET("api/user/{GROUP_USER}/{ID_USER}")
    Call<ResponseUser> dataUser(
            @Path("GROUP_USER") Integer GROUP_USER,
            @Path("ID_USER") Integer ID_USER
    );


    @GET("api/pesanan")
    Call<ResponseTransaksi>  dataTransaksi();

    @GET("api/dashboard")
    Call<ResponseInfoDashboard>  dataInfoDashboard();

    @FormUrlEncoded
    @POST("api/pesanan")
    Call<ResponseRegisterTransaksi> checkout(@Field("ID_USER") String ID_USER,
                                             @Field("TOTAL_PEMBAYARAN") String TOTAL_PEMBAYARAN,
                                             @Field("LIST_CART") String LIST_CART
                                             );

    @GET("api/pesanan/{KODE_TRANSAKSI}")
    Call<ResponseDetailTransaksi> dataDetailTransaksi(
            @Path("KODE_TRANSAKSI") String KODE_TRANSAKSI
    );

    @GET("api/pesanan/history/{ID_USER}")
    Call<ResponseHistory> dataHistory(
            @Path("ID_USER") Integer ID_USER
    );

//    @FormUrlEncoded
//    @POST("api/pesanan")
//    Call<ResponseRegisterTransaksi> checkout(@Body DataCarts data);


}
