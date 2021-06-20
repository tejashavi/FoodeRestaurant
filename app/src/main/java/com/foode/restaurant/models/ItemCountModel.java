package com.foode.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ItemCountModel {

    @Expose
    @SerializedName("PRODUCT")
    private int product;
    @Expose
    @SerializedName("CATEGORY")
    private int category;
    @Expose
    @SerializedName("STATUS")
    private String status;

    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
