package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DatosServices {
    @GET("reviews/search.json?query=godfather&api-key=3fmJUixuvQthMwCiGooWSLhpfRccAhiF")
    Call<pelis> getDatos();
    @GET("api/games")
    Call<List<sport>> getDatos2();
}
