package com.foode.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ProductModel {

    @Expose
    @SerializedName("DATA")
    private List<DATA> data;
    @Expose
    @SerializedName("STATUS")
    private String status;

    public List<DATA> getData() {
        return data;
    }

    public void setData(List<DATA> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DATA {
        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("orignal_price")
        private int orignalPrice;
        @Expose
        @SerializedName("price")
        private int price;
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private String id;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getOrignalPrice() {
            return orignalPrice;
        }

        public void setOrignalPrice(int orignalPrice) {
            this.orignalPrice = orignalPrice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
