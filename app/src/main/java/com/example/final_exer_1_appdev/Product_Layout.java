package com.example.final_exer_1_appdev;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Product_Layout extends Fragment {


    public Product_Layout() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product__layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int[] images = {
                R.drawable.monitor,
                R.drawable.ram,
                R.drawable.mouse,
                R.drawable.motherboard,
                R.drawable.psu,
                R.drawable.gpu,
                R.drawable.cooler,
                R.drawable.ssd,
                R.drawable.pc_case,
                R.drawable.thermal_paste
        };

        RecyclerView recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] names = getResources().getStringArray(R.array.Pc_parts);
        String[] prices = getResources().getStringArray(R.array.Pricing);
        String[] desc = getResources().getStringArray(R.array.ProductDescriptions); // ✅ ADD THIS

        ProductAdapter adapter =
                new ProductAdapter(getContext(), names, prices, desc, images); // ✅ FIXED

        recyclerView.setAdapter(adapter);
    }
}