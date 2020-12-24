package com.example.v_layout.api;

import com.example.v_layout.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<Bean> get();
}
