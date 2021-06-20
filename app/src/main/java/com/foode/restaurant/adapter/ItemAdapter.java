package com.foode.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.models.PendingOrderModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    List<PendingOrderModel.Order_info> orderInfo;

    public ItemAdapter(Context context, List<PendingOrderModel.Order_info> orderInfo) {
        this.context = context;
        this.orderInfo = orderInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvOrderItem.setText(orderInfo.get(position).getQuantity()+" X "+orderInfo.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return orderInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderItem=itemView.findViewById(R.id.tvOrderItem);
        }
    }
}
