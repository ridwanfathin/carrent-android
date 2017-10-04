package com.rental_apps.android.rental_apps.model.model_mobil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Muhajir on 03/09/2017.
 */
import java.util.List;

public class ResponseCars {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataCars> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataCars> getData() {
        return data;
    }

    public void setData(List<DataCars> data) {
        this.data = data;
    }

}
