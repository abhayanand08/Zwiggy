package com.example.zwiggy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zwiggy.adapter.AsiaFoodAdapter;
import com.example.zwiggy.adapter.FeaturesAppAdapter;
import com.example.zwiggy.adapter.PopularFoodAdapter;
import com.example.zwiggy.model.AsiaFood;
import com.example.zwiggy.model.FeaturesApp;
import com.example.zwiggy.model.PopularFood;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class mainpage extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler, featuresrecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;
    FeaturesAppAdapter featuresAppAdapter;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);




        List<FeaturesApp> featuresAppList = new ArrayList<>();

        featuresAppList.add(new FeaturesApp("Food Delivery"));
        featuresAppList.add(new FeaturesApp("Great Offers"));
        featuresAppList.add(new FeaturesApp("Heavy Discount"));
        featuresAppList.add(new FeaturesApp("New Arrival"));
        featuresAppList.add(new FeaturesApp("Pure Veg"));

        setFeatureRecycler(featuresAppList);


        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Float Cake Vietnam", "$7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "$17.05", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "$25.05", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Float Cake Vietnam", "$7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "$17.05", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "$25.05", R.drawable.popularfood3));

        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));

        setAsiaRecycler(asiaFoodList);

    }

    private void setFeatureRecycler(List<FeaturesApp> featuresAppList) {

        featuresrecycler = findViewById(R.id.features_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        featuresrecycler.setLayoutManager(layoutManager);
        featuresAppAdapter = new FeaturesAppAdapter(this, featuresAppList);
        featuresrecycler.setAdapter(featuresAppAdapter);

    }

    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }


}