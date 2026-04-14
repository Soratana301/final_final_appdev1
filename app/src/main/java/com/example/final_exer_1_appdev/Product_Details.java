package com.example.final_exer_1_appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Product_Details extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product__details, container, false);

        ImageView imageView = view.findViewById(R.id.detailImage);
        TextView nameView = view.findViewById(R.id.detailName);
        TextView priceView = view.findViewById(R.id.detailPrice);
        TextView descView = view.findViewById(R.id.detailDescription);

        Button addCartBtn = view.findViewById(R.id.addcartbtn);
        Button gotoCartBtn = view.findViewById(R.id.gotocartbtn);

        EditText quantityInput = view.findViewById(R.id.quantityInput);

        Bundle bundle = getArguments();

        final int imageRes = (bundle != null)
                ? bundle.getInt("image")
                : R.drawable.ic_launcher_foreground;

        if (bundle != null) {
            nameView.setText(bundle.getString("name"));
            priceView.setText(bundle.getString("price"));
            descView.setText(bundle.getString("desc"));
            imageView.setImageResource(imageRes);
        }

        addCartBtn.setOnClickListener(v -> {

            String qtyText = quantityInput.getText().toString().trim();


            if (qtyText.isEmpty()) {
                quantityInput.setError("Enter quantity");
                return;
            }

            int qty;

            try {
                qty = Integer.parseInt(qtyText);
            } catch (Exception e) {
                quantityInput.setError("Invalid number");
                return;
            }

            if (qty <= 0) {
                quantityInput.setError("Must be 1 or more");
                return;
            }

            String name = nameView.getText().toString();
            String price = priceView.getText().toString();

            cart_Layout.cartNames.add(name);
            cart_Layout.cartPrices.add(price);
            cart_Layout.cartQuantities.add(String.valueOf(qty));
            cart_Layout.cartImages.add(imageRes);

            Toast.makeText(getContext(), "Added " + qty + " item(s)", Toast.LENGTH_SHORT).show();
        });

        // GO TO CART
        gotoCartBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_product_Details_to_cart_Layout);
        });

        return view;
    }
}