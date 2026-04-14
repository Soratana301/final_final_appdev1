package com.example.final_exer_1_appdev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    String[] names;
    String[] prices;
    String[] descriptions;
    int[] images;

    public ProductAdapter(Context context,
                          String[] names,
                          String[] prices,
                          String[] descriptions,
                          int[] images){
        this.context = context;
        this.names = names;
        this.prices = prices;
        this.images = images;
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context)
                .inflate(R.layout.product_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        holder.name.setText(names[position]);
        holder.price.setText(prices[position]);
        holder.image.setImageResource(images[position]);

        holder.itemView.setOnClickListener(v -> {

            android.os.Bundle bundle = new android.os.Bundle();

            bundle.putString("name", names[position]);
            bundle.putString("price", prices[position]);
            bundle.putString("desc", descriptions[position]);
            bundle.putInt("image", images[position]);

            androidx.navigation.Navigation.findNavController(v)
                    .navigate(R.id.action_product_Layout_to_product_Details, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.PrdName);
            price = itemView.findViewById(R.id.PrdPrice);
            image = itemView.findViewById(R.id.PrdImage);
        }
    }
}
