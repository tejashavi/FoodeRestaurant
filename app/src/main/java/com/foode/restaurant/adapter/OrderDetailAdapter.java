package com.foode.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.models.PendingOrderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    Context context;
    List<PendingOrderModel.Order_info> pendingOrderModel;

    public OrderDetailAdapter(Context context, List<PendingOrderModel.Order_info> pendingOrderModel) {
        this.context = context;
        this.pendingOrderModel = pendingOrderModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(pendingOrderModel.get(0).getUrl()).placeholder(R.drawable.ic_baseline_person_24).into(holder.ivOrderImage);
        holder.tvProductName.setText(pendingOrderModel.get(position).getName());
        holder.tvQuantity.setText("Quantity: " + pendingOrderModel.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return pendingOrderModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivOrderImage;
        TextView tvProductName,
                tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivOrderImage = itemView.findViewById(R.id.ivOrderImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
