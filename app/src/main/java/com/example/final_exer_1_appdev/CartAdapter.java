package com.example.final_exer_1_appdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<String> names;
    ArrayList<String> prices;
    ArrayList<String> quantities;
    ArrayList<Integer> images;

    public CartAdapter(ArrayList<String> names,
                       ArrayList<String> prices,
                       ArrayList<String> quantities,
                       ArrayList<Integer> images) {

        this.names = names;
        this.prices = prices;
        this.quantities = quantities;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name = names.get(position);
        String priceStr = prices.get(position);
        String qtyStr = quantities.get(position);

        priceStr = priceStr.replace("₱", "").replace(",", "").trim();

        int price = 0;
        int qty = 1;

        try { price = Integer.parseInt(priceStr); } catch (Exception ignored) {}
        try { qty = Integer.parseInt(qtyStr); } catch (Exception ignored) {}

        int total = price * qty;

        holder.name.setText(name);
        holder.qty.setText("Qty: " + qty);
        holder.total.setText("Total: ₱" + total);
        holder.image.setImageResource(images.get(position));

        holder.removeBtn.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {
                names.remove(pos);
                prices.remove(pos);
                quantities.remove(pos);
                images.remove(pos);

                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, names.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, qty, total;
        Button removeBtn;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cartName);
            qty = itemView.findViewById(R.id.cartQty);
            total = itemView.findViewById(R.id.cartTotal);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            image = itemView.findViewById(R.id.cartImage);
        }
    }
}