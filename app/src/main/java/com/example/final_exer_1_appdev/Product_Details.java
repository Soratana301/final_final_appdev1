package com.example.final_exer_1_appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Product_Details extends Fragment {

    public Product_Details() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product__details, container, false);

        ImageView imageView = view.findViewById(R.id.detailImage);
        TextView nameView = view.findViewById(R.id.detailName);
        TextView priceView = view.findViewById(R.id.detailPrice);
        TextView descView = view.findViewById(R.id.detailDescription);

        Bundle bundle = getArguments();

        if (bundle != null) {
            nameView.setText(bundle.getString("name"));
            priceView.setText(bundle.getString("price"));
            descView.setText(bundle.getString("desc"));
            imageView.setImageResource(bundle.getInt("image"));
        }

        return view;
    }
}