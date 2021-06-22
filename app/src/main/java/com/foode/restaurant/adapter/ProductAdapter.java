package com.foode.restaurant.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.ProductActivity;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.interfaces.UpdateInventory;
import com.foode.restaurant.models.CommonModel;
import com.foode.restaurant.models.ProductModel;
import com.foode.restaurant.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ProductModel productModel;
    UpdateInventory updateInventory;

    public ProductAdapter(Context context, ProductModel productModel, UpdateInventory updateInventory) {
        this.context = context;
        this.productModel = productModel;
        this.updateInventory = updateInventory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(productModel.getData().get(position).getUrl()).placeholder(R.mipmap.placeholder).into(holder.ivProductImage);
        holder.tvProductName.setText(productModel.getData().get(position).getName());
        holder.tvPrice.setText("Rs " + productModel.getData().get(position).getPrice());
        if (productModel.getData().get(position).getStatus().equalsIgnoreCase("enabled")) {
            holder.switchOnOf.setChecked(true);
        } else {
            holder.switchOnOf.setChecked(false);
        }
/*
        holder.switchOnOf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ProductActivity.productModel.getData().get(position).setStatus("enabled");

                } else {
                    ProductActivity.productModel.getData().get(position).setStatus("disabled");
                }
                notifyDataSetChanged();
                //updateInventory.updateStatus(productModel.getData().get(position).getId(), productModel);
            }
        });
*/
        holder.switchOnOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                HashMap<String, String> hm = new HashMap<>();
                hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
                hm.put("product_id", productModel.getData().get(position).getId());
                Call<CommonModel> commonModelCall = apiInterface.updateInventory(hm);
                commonModelCall.enqueue(new Callback<CommonModel>() {
                    @Override
                    public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                        CommonModel commonModel = response.body();
                        if (commonModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                            if (holder.switchOnOf.isChecked() == true) {
                                ProductActivity.productModel.getData().get(position).setStatus("enabled");
                            } else {
                                ProductActivity.productModel.getData().get(position).setStatus("disabled");
                            }
                            notifyDataSetChanged();
                        } else {
                            AppUtils.showToastSort(context, "Something went wrong");
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonModel> call, Throwable t) {

                    }
                });


                // updateInventory.updateStatus(productModel.getData().get(position).getId(), productModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModel.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName, tvPrice;
        Switch switchOnOf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            switchOnOf = itemView.findViewById(R.id.switchOnOf);
        }
    }
}
