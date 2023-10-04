package com.example.exercise08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercise08.R;
import com.example.exercise08.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private Context context;
    private List<Product> mArrayListProduct;

    public AdapterProduct(Context context, List<Product> mArrayListProduct) {
        this.context = context;
        this.mArrayListProduct = mArrayListProduct;
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        Product product = mArrayListProduct.get(position);
        if (product == null) {
            return;
        }

        Glide.with(holder.itemView.getContext())
                .load(product.getThumbnail())
                .into(holder.imgProduct);
        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText("$" + product.getPrice());
        holder.tvRate.setText(String.valueOf(product.getRating()));
    }

    @Override
    public int getItemCount() {
        return mArrayListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView tvTitle;

        private TextView tvPrice;

        private TextView tvRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRate = itemView.findViewById(R.id.tvRate);
        }
    }
}
