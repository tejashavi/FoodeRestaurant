package com.foode.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopStatusModel {

    @Expose
    @SerializedName("MESSAGE")
    private String message;
    @Expose
    @SerializedName("SHOP_STATUS")
    private String shopStatus;
    @Expose
    @SerializedName("STATUS")
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
