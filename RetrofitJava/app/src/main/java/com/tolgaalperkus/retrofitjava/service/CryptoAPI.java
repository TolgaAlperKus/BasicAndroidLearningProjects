package com.tolgaalperkus.retrofitjava.service;

import com.tolgaalperkus.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //GET , POST, UPDATE, DELETE
    @GET("prices?key=297a3e6af3c71f34b3b9668232a5ff37")
    //Call<List<CryptoModel>> getData();
    Observable<List<CryptoModel>> getData();

}
