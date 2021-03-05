package com.foode.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Login {


    @Expose
    @SerializedName("DATA")
    private List<DATA> data;
    @Expose
    @SerializedName("MESSAGE")
    private String message;
    @Expose
    @SerializedName("STATUS")
    private String status;

    public List<DATA> getData() {
        return data;
    }

    public void setData(List<DATA> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DATA {
        @Expose
        @SerializedName("updated_at")
        private String updatedAt;
        @Expose
        @SerializedName("created_at")
        private String createdAt;
        @Expose
        @SerializedName("device_type")
        private String deviceType;
        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("rating_status")
        private int ratingStatus;
        @Expose
        @SerializedName("rating")
        private int rating;
        @Expose
        @SerializedName("popular")
        private int popular;
        @Expose
        @SerializedName("pure_veg")
        private int pureVeg;
        @Expose
        @SerializedName("longitude")
        private double longitude;
        @Expose
        @SerializedName("latitude")
        private double latitude;
        @Expose
        @SerializedName("maps_address")
        private String mapsAddress;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("estimated_delivery_time")
        private int estimatedDeliveryTime;
        @Expose
        @SerializedName("offer_percent")
        private int offerPercent;
        @Expose
        @SerializedName("offer_min_amount")
        private int offerMinAmount;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("default_banner")
        private String defaultBanner;
        @Expose
        @SerializedName("avatar")
        private String avatar;
        @Expose
        @SerializedName("phone")
        private String phone;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getRatingStatus() {
            return ratingStatus;
        }

        public void setRatingStatus(int ratingStatus) {
            this.ratingStatus = ratingStatus;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getPopular() {
            return popular;
        }

        public void setPopular(int popular) {
            this.popular = popular;
        }

        public int getPureVeg() {
            return pureVeg;
        }

        public void setPureVeg(int pureVeg) {
            this.pureVeg = pureVeg;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getMapsAddress() {
            return mapsAddress;
        }

        public void setMapsAddress(String mapsAddress) {
            this.mapsAddress = mapsAddress;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getEstimatedDeliveryTime() {
            return estimatedDeliveryTime;
        }

        public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
            this.estimatedDeliveryTime = estimatedDeliveryTime;
        }

        public int getOfferPercent() {
            return offerPercent;
        }

        public void setOfferPercent(int offerPercent) {
            this.offerPercent = offerPercent;
        }

        public int getOfferMinAmount() {
            return offerMinAmount;
        }

        public void setOfferMinAmount(int offerMinAmount) {
            this.offerMinAmount = offerMinAmount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDefaultBanner() {
            return defaultBanner;
        }

        public void setDefaultBanner(String defaultBanner) {
            this.defaultBanner = defaultBanner;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
