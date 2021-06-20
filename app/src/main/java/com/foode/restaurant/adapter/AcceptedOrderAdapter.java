package com.foode.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.OrderDetailActivity;
import com.foode.restaurant.models.PendingOrderModel;
import com.squareup.picasso.Picasso;

public class AcceptedOrderAdapter extends RecyclerView.Adapter<AcceptedOrderAdapter.ViewHolder> {
    Context context;
    PendingOrderModel pendingOrderModel;
    public AcceptedOrderAdapter(Context context, PendingOrderModel pendingOrderModel) {
        this.context = context;
        this.pendingOrderModel = pendingOrderModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_accepted_order, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(pendingOrderModel.getData().get(position).getUserInfo().getUserProfilePic()).placeholder(R.drawable.ic_baseline_person_24).into(holder.ivUserProfile);
        holder.tvUserName.setText(pendingOrderModel.getData().get(position).getUserInfo().getUserName());
        holder.tvPaymentMode.setText(pendingOrderModel.getData().get(position).getUserInfo().getPayment_mode());
        holder.tvTotalAmount.setText("Rs" + pendingOrderModel.getData().get(position).getUserInfo().getPayableAmount());
        holder.tvDropLocation.setText("Deliver To : " + pendingOrderModel.getData().get(position).getDropLocation().getMapAddress());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        ItemAdapter itemAdapter = new ItemAdapter(context, pendingOrderModel.getData().get(position).getOrderInfo());
        holder.rvList.setLayoutManager(gridLayoutManager);
        holder.rvList.setAdapter(itemAdapter);

        holder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderDetailActivity.class).putExtra("id",pendingOrderModel.getData().get(position).getUserInfo().getOrderId()+""));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingOrderModel.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlMain;
        ImageView ivUserProfile;
        TextView tvUserName, tvPaymentMode, tvTotalAmount, tvDropLocation;
        RecyclerView rvList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlMain = itemView.findViewById(R.id.rlMain);
            ivUserProfile = itemView.findViewById(R.id.ivUserProfile);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvPaymentMode = itemView.findViewById(R.id.tvPaymentMode);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            tvDropLocation = itemView.findViewById(R.id.tvDropLocation);
            rvList = itemView.findViewById(R.id.rvList);

        }
    }
}
