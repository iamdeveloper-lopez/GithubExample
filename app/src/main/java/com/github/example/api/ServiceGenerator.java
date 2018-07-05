package com.github.example.api;

import com.github.example.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(serviceClass);
    }

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setLenient()
            .setPrettyPrinting()
            .create();

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static Interceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

}
