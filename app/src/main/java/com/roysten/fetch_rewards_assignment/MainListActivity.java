package com.roysten.fetch_rewards_assignment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.roysten.fetch_rewards_assignment.adapters.DataAdapter;
import com.roysten.fetch_rewards_assignment.models.Data;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

public class MainListActivity extends AppCompatActivity {

    public static final String JSON_DATA_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    public static final String TAG = "MainListActivity";

    List<Data> dataList;
    List<Integer> uniqueIdList;
    RecyclerView rvMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.ic_launcher_background);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        rvMainList = findViewById(R.id.rvMainList);
        dataList = new ArrayList<>();
        uniqueIdList = new ArrayList<>();

        //create adapter
        final DataAdapter dataAdapter = new DataAdapter(this, dataList, uniqueIdList);

        //set adapter to recyclerView
        rvMainList.setAdapter(dataAdapter);

        //set a Layout Manager on the recycler view
        rvMainList.setLayoutManager(new LinearLayoutManager(this));

        //AsyncHttpClient Network call to fetch movie data
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(JSON_DATA_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray jsonArray = json.jsonArray;
//                    Log.i(TAG, "Json Data: " + jsonArray.toString());
//                    Log.i(TAG, "Json List Count: " + dataList.size());
                    dataList = Data.fromJsonArray(jsonArray);
                    Map<Integer, List<String>> map = Data.getMap(dataList);

                    for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                        Integer keyListId = entry.getKey();
                        List<String> nameValues = entry.getValue();
                        uniqueIdList.add(keyListId);
                        Log.i(TAG, "ListID: " + keyListId + " names: " + nameValues.toString());
                    }


//                    dataList.addAll(Data.fromJsonArray(jsonArray));


                    dataAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception Occurred!", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                //Log.d(TAG, "onFailure");
            }
        });


    }
}