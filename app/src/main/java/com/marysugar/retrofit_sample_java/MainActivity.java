package com.marysugar.retrofit_sample_java;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    ArrayList<Country> countries= new ArrayList<>();
    private ProgressDialog progressDialog;
    ListView listView;

    // Base URL
    public static final String BASE_URL = "https://cdn.rawgit.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFetch = findViewById(R.id.btn_fetch);

        activity = this;
        listView = findViewById(R.id.list);
        buttonFetch.setOnClickListener(v -> {
            countries.clear();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching Data");
            progressDialog.setCancelable(false);
            progressDialog.show();

            getWebServiceResponseData();
        });
    }

    private void getWebServiceResponseData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CountryAPI api = retrofit.create(CountryAPI.class);
        Call<List<Country>> call = api.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                try {
                    countries = (ArrayList)response.body();
                    Log.d("Data", countries.get(0).countryName);

                    if (progressDialog.isShowing()) progressDialog.dismiss();
                    CustomCountryListAdapter adapter = new CustomCountryListAdapter(activity, countries);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener((parent, view, position, id) ->
                            Toast.makeText(
                            getApplicationContext(),
                            "You Selected "+countries.get(position).getCountryName()+ " as Country",
                            Toast.LENGTH_SHORT
                            ).show());
                } catch (Exception e) {
                    Log.e("onResponse", "There is an Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("Failure", t.toString());
            }
        });

    }
}