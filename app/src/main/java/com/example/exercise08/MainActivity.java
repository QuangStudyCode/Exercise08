package com.example.exercise08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.exercise08.adapter.AdapterProduct;
import com.example.exercise08.api.ApiService;
import com.example.exercise08.model.Product;
import com.example.exercise08.model.ProductsResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterProduct adapterProduct;
    private List<Product> productArrayList;

    private ProductsResponse productsResponse;

    private ImageSlider imageSlider;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRc);
        imageSlider = findViewById(R.id.imgSlider);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_home){
                    Toast.makeText(getApplicationContext(),"OKOKOKO",Toast.LENGTH_SHORT).show();
                    return true;
                }else if(item.getItemId() == R.id.wish){
                    Toast.makeText(getApplicationContext(),"OKOKOKO",Toast.LENGTH_SHORT).show();
                    return true;
                }else if(item.getItemId() == R.id.category){
                    Toast.makeText(getApplicationContext(),"OKOKOKO",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        callAPI();
//      initData();

//      Log.d("TAG", "onCreate: "+productArrayList.size());
    }

    private void initData() {
        for (Product product : productArrayList) {
            product.getTitle();
        }
    }

    private void initView() {
        if (productArrayList != null && !productArrayList.isEmpty()) {
            adapterProduct = new AdapterProduct(getApplicationContext(), productArrayList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapterProduct);
        } else {
            // Hiển thị thông báo hoặc thực hiện các hành động khác khi danh sách sản phẩm rỗng
            Toast.makeText(getApplicationContext(), "Danh sách sản phẩm rỗng", Toast.LENGTH_SHORT).show();
        }
    }

    private void callAPI() {
        ApiService apiService = RetrofitClient.create(ApiService.class);
        apiService.getProduct().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        productsResponse = new ProductsResponse();
                        productsResponse = response.body();
                        Log.d("TAG", "onResponse: " + productsResponse.getProducts().size());

//                        initData
                        productArrayList = new ArrayList<>();
                        if (productsResponse != null) {
                            productArrayList.addAll(productsResponse.getProducts());
                            initView();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_SHORT).show();
            }
        });
    }
}