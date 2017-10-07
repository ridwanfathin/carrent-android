package com.rental_apps.android.rental_apps.SPreferenced;

/**
 * Created by Muhajir on 29/09/2017.
 */

public class SPref {
    private static String ID_USER="ID_USER";
    private static String USERNAME="USERNAME";
    private static String NAME="NAME";
    private static String EMAIL="EMAIL";
    private static String NO_TELP="NO_TELP";
    private static String JENIS_KELAMIN="JENIS_KELAMIN";
    private static String PHOTO="PHOTO";
    private static String LAST_UPDATE="LAST_UPDATE";
    private static String ALAMAT="ALAMAT";
    private static String GROUP_USER="GROUP_USER";
    private static String PASSWORD="PASSWORD";

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        SPref.PASSWORD = PASSWORD;
    }

    public static String getIdUser() {
        return ID_USER;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getNoTelp() {
        return NO_TELP;
    }

    public static String getJenisKelamin() {
        return JENIS_KELAMIN;
    }

    public static String getPHOTO() {
        return PHOTO;
    }

    public static String getLastUpdate() {
        return LAST_UPDATE;
    }

    public static String getALAMAT() {
        return ALAMAT;
    }

    public static String getGroupUser() {
        return GROUP_USER;
    }
}
