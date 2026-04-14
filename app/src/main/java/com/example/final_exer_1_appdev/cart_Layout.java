package com.example.final_exer_1_appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cart_Layout extends Fragment {

    RecyclerView recyclerView;
    CartAdapter adapter;
    TextView totalPriceText;

    // CART DATA

    public static ArrayList<String> cartNames = new ArrayList<>();
    public static ArrayList<String> cartPrices = new ArrayList<>();
    public static ArrayList<String> cartQuantities = new ArrayList<>();
    public static ArrayList<Integer> cartImages = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart__layout, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        totalPriceText = view.findViewById(R.id.totalPriceText);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CartAdapter(
                cartNames,
                cartPrices,
                cartQuantities,
                cartImages
        );

        recyclerView.setAdapter(adapter);

        calculateTotal();

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                calculateTotal();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                calculateTotal();
            }
        });

        return view;
    }

    private void calculateTotal() {

        int total = 0;

        for (int i = 0; i < cartPrices.size(); i++) {

            String priceStr = cartPrices.get(i)
                    .replace("₱", "")
                    .replace(",", "")
                    .trim();

            int price = 0;
            int qty = 1;

            try { price = Integer.parseInt(priceStr); } catch (Exception ignored) {}
            try { qty = Integer.parseInt(cartQuantities.get(i)); } catch (Exception ignored) {}

            total += price * qty;
        }

        totalPriceText.setText("Total: ₱" + total);
    }
}