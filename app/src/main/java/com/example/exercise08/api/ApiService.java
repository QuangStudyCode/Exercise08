package com.example.exercise08.api;

import com.example.exercise08.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<ProductsResponse> getProduct();

}
