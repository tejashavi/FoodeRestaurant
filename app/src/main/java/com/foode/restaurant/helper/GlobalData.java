package com.foode.restaurant.helper;

import android.location.Location;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tamil on 9/22/2017.
 */

public class GlobalData {

    private static final GlobalData ourInstance = new GlobalData();
    public static double latitude;
    public static double longitude;
    public static String addressHeader = "";
    public static Location CURRENT_LOCATION = null;

    /*------------Filter--------*/
    public static boolean isPureVegApplied = false;
    public static boolean isOfferApplied = false;
    public static boolean shouldContinueService = false;
    public static ArrayList<Integer> cuisineIdArrayList = null;
    public static boolean isCardChecked = false;
    public static String loginBy = "manual";
    public static String name, email, access_token, imageUrl;
    public static String address = "";
    public static int addCartShopId = 0;

    public static List<String> ORDER_STATUS = Arrays.asList("ORDERED", "RECEIVED", "ASSIGNED", "PROCESSING", "REACHED", "PICKEDUP", "ARRIVED", "COMPLETED");

    public static int otpValue = 0;
    public static String mobile = "";
    public static String currencySymbol = "₹";
    public static int notificationCount = 0;


    public static ArrayList<HashMap<String, String>> foodCart;
    public static String accessToken = "";

    private GlobalData() {
    }

    public static GlobalData getInstance() {
        return ourInstance;
    }

    public static NumberFormat getNumberFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        numberFormat.setCurrency(Currency.getInstance("INR"));
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat;
    }
}
