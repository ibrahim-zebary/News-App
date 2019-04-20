package com.example.ibrahim.retrofitguardian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ibrahim.retrofitguardian.adapter.RecyclerAdapter;
import com.example.ibrahim.retrofitguardian.model.Root;
import com.example.ibrahim.retrofitguardian.rest.ApiClient;
import com.example.ibrahim.retrofitguardian.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    ImageView noDataImageView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;

    ConstraintLayout constraintLayout;
    Button tryAgainButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        constraintLayout = findViewById(R.id.no_internet_layout);
        noDataImageView = findViewById(R.id.no_data_imageview);
        tryAgainButton = findViewById(R.id.try_again);
        progressBar = findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.VISIBLE);

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    constraintLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    sendRequest(loadJson());

                } else {
                    View view = findViewById(R.id.activity_main);
                    Helper.showSnackBar(view, "No Internet Connection");
                    constraintLayout.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


        if (isConnected()) {
            sendRequest(loadJson());
            constraintLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

        } else {
            View view = findViewById(R.id.activity_main);
            Helper.showSnackBar(view, "No Internet Connection");
            constraintLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        sendRequest(loadJson());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.application_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
        }
        return true;
    }

    private Call<Root> loadJson() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sectionName = sharedPreferences.getString(getString(R.string.section_target), null);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Root> call = apiInterface.getRoot(sectionName);

        return call;
    }


    private void sendRequest(Call<Root> call) {
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();

                if (root.getResponse().getResults().size() <= 0) {

                    recyclerView.setVisibility(View.GONE);
                    noDataImageView.setVisibility(View.VISIBLE);

                    View view = findViewById(R.id.activity_main);
                    Helper.showSnackBar(view, "No Data Available");
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    noDataImageView.setVisibility(View.GONE);
                    adapter = new RecyclerAdapter(root.getResponse().getResults());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
