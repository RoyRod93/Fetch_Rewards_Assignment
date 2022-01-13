package com.roysten.fetch_rewards_assignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roysten.fetch_rewards_assignment.NamesListActivity;
import com.roysten.fetch_rewards_assignment.R;
import com.roysten.fetch_rewards_assignment.models.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    List<Data> dataList;
    List<Integer> uniqueIdList;
    Map<Integer, List<String>> map;
    int clickId;

    public DataAdapter(Context context, List<Data> dataList, List<Integer> uniqueIdList) {
        this.context = context;
        this.dataList = dataList;
        this.uniqueIdList = uniqueIdList;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(context).inflate(R.layout.main_list_item_layout, parent, false);
        return new ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        clickId = uniqueIdList.get(position);
        holder.bind(clickId);

    }

    @Override
    public int getItemCount() {
        return uniqueIdList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout listIdContainer;
        TextView tvListId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listIdContainer = itemView.findViewById(R.id.listIdContainer);
            tvListId = itemView.findViewById(R.id.tvListId);
        }

        public void bind(final int listId) {
            tvListId.setText(String.valueOf(listId));
            Integer key = listId;
            map = Data.getMap(dataList);
            ArrayList<String> namesList = (ArrayList<String>) map.get(key);

            listIdContainer.setOnClickListener(v -> {
                Intent intent = new Intent(context, NamesListActivity.class);
                intent.putStringArrayListExtra("namesList", namesList);
                context.startActivity(intent);

            });

        }
    }


}
