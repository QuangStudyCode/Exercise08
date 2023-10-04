package com.example.exercise08;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://dummyjson.com/";

    private static Retrofit mInstance;


    public static Retrofit getInstance() {
        if (mInstance == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            mInstance = new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return mInstance;
    }

    public static <T> T create(Class<T> tClass) {
        return getInstance().create(tClass);
    }
}
