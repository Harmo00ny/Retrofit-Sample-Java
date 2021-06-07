package com.marysugar.retrofit_sample_java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {
    @GET("/arpitmandliya/AndroidRestJSONExample/master/countries.json")
    Call<List<Country>> getCountries();
}
