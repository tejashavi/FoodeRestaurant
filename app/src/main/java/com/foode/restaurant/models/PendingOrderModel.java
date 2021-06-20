package com.foode.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public  class PendingOrderModel implements Serializable {

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
        @SerializedName("drop_location")
        private DropLocation dropLocation;
        @Expose
        @SerializedName("order_info")
        private List<Order_info> orderInfo;
        @Expose
        @SerializedName("user_info")
        private UserInfo userInfo;

        public DropLocation getDropLocation() {
            return dropLocation;
        }

        public void setDropLocation(DropLocation dropLocation) {
            this.dropLocation = dropLocation;
        }

        public List<Order_info> getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(List<Order_info> orderInfo) {
            this.orderInfo = orderInfo;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }
    }

    public static class DropLocation implements Serializable {
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("longitude")
        private double longitude;
        @Expose
        @SerializedName("latitude")
        private double latitude;
        @Expose
        @SerializedName("map_address")
        private String mapAddress;
        @Expose
        @SerializedName("landmark")
        private String landmark;
        @Expose
        @SerializedName("pincode")
        private String pincode;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("state")
        private String state;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("street")
        private String street;
        @Expose
        @SerializedName("building")
        private String building;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getMapAddress() {
            return mapAddress;
        }

        public void setMapAddress(String mapAddress) {
            this.mapAddress = mapAddress;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }
    }

    public static class Order_info implements Serializable{
        @Expose
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("out_of_stock")
        private String outOfStock;
        @Expose
        @SerializedName("addon_status")
        private int addonStatus;
        @Expose
        @SerializedName("featured")
        private int featured;
        @Expose
        @SerializedName("max_quantity")
        private int maxQuantity;
        @Expose
        @SerializedName("avalability")
        private int avalability;
        @Expose
        @SerializedName("food_type")
        private String foodType;
        @Expose
        @SerializedName("position")
        private String position;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("shop_id")
        private int shopId;
        @Expose
        @SerializedName("parent_id")
        private int parentId;
        @Expose
        @SerializedName("deleted_at")
        private String deletedAt;
        @Expose
        @SerializedName("updated_at")
        private String updatedAt;
        @Expose
        @SerializedName("created_at")
        private String createdAt;
        @Expose
        @SerializedName("savedforlater")
        private int savedforlater;
        @Expose
        @SerializedName("note")
        private String note;
        @Expose
        @SerializedName("quantity")
        private int quantity;
        @Expose
        @SerializedName("order_id")
        private int orderId;
        @Expose
        @SerializedName("promocode_id")
        private String promocodeId;
        @Expose
        @SerializedName("product_id")
        private int productId;
        @Expose
        @SerializedName("user_id")
        private int userId;
        @Expose
        @SerializedName("id")
        private int id;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOutOfStock() {
            return outOfStock;
        }

        public void setOutOfStock(String outOfStock) {
            this.outOfStock = outOfStock;
        }

        public int getAddonStatus() {
            return addonStatus;
        }

        public void setAddonStatus(int addonStatus) {
            this.addonStatus = addonStatus;
        }

        public int getFeatured() {
            return featured;
        }

        public void setFeatured(int featured) {
            this.featured = featured;
        }

        public int getMaxQuantity() {
            return maxQuantity;
        }

        public void setMaxQuantity(int maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public int getAvalability() {
            return avalability;
        }

        public void setAvalability(int avalability) {
            this.avalability = avalability;
        }

        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }

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

        public int getSavedforlater() {
            return savedforlater;
        }

        public void setSavedforlater(int savedforlater) {
            this.savedforlater = savedforlater;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getPromocodeId() {
            return promocodeId;
        }

        public void setPromocodeId(String promocodeId) {
            this.promocodeId = promocodeId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class UserInfo implements Serializable{
        @Expose
        @SerializedName("user_contact_no")
        private String user_contact_no;

        public String getUser_contact_no() {
            return user_contact_no;
        }

        public void setUser_contact_no(String user_contact_no) {
            this.user_contact_no = user_contact_no;
        }

        @Expose
        @SerializedName("payment_mode")
        private String payment_mode;
        @Expose
        @SerializedName("payable_amount")
        private int payableAmount;
        @Expose
        @SerializedName("net_amount")
        private int netAmount;
        @Expose
        @SerializedName("total_items")
        private int totalItems;
        @Expose
        @SerializedName("order_id")
        private int orderId;
        @Expose
        @SerializedName("user_profile_pic")
        private String userProfilePic;
        @Expose
        @SerializedName("user_name")
        private String userName;
        @Expose
        @SerializedName("user_id")
        private int userId;


        public String getPayment_mode() {
            return payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

        public int getPayableAmount() {
            return payableAmount;
        }

        public void setPayableAmount(int payableAmount) {
            this.payableAmount = payableAmount;
        }

        public int getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(int netAmount) {
            this.netAmount = netAmount;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getUserProfilePic() {
            return userProfilePic;
        }

        public void setUserProfilePic(String userProfilePic) {
            this.userProfilePic = userProfilePic;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
