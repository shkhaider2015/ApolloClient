package com.example.apolloclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mytext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI();
            }
        });


    }

    private void callAPI()
    {
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
                .build();

// Then enqueue your query
        apolloClient.query(new LaunchDetailsQuery("80"))
                .enqueue(new ApolloCall.Callback<LaunchDetailsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<LaunchDetailsQuery.Data> response) {
                        Log.e("Apollo", "Launch site: " + response.getData().launch.site);
                        Log.e("Apollo", "Launch id: " + response.getData().launch.id);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("Apollo", "Error", e);
                    }
                });
    }
}