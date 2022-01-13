package com.roysten.fetch_rewards_assignment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roysten.fetch_rewards_assignment.adapters.NamesAdapter;

import java.util.List;

public class NamesListActivity extends AppCompatActivity {

    public static final String TAG = "NamesListActivity";
    RecyclerView rvNamesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_list);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvNamesList = findViewById(R.id.rvNamesList);

        final List<String> namesList = getIntent().getStringArrayListExtra("namesList");
        Log.i(TAG, " names: " + namesList.toString());

        final NamesAdapter namesAdapter = new NamesAdapter(this, namesList);
        rvNamesList.setAdapter(namesAdapter);
        rvNamesList.setLayoutManager(new LinearLayoutManager(this));

        namesAdapter.notifyDataSetChanged();
    }
}
