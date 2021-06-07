package com.marysugar.retrofit_sample_java;

public class Country {
    int id;
    String countryName;

    public Country(int id, String countryName) {
        super();
        this.id = id;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }
}
