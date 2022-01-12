package com.roysten.fetch_rewards_assignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roysten.fetch_rewards_assignment.R;
import com.roysten.fetch_rewards_assignment.models.Data;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    Context context;
    List<Data> dataList;
    List<Integer> uniqueIdList;

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
//        Data data = dataList.get(position);
        int listId = uniqueIdList.get(position);
        holder.bind(listId);

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

//            moviesContainer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("movieObj", Parcels.wrap(movie));
//                    Pair<View, String> p1 = Pair.create((View) tvMovieTitle, "titleTransit");
//                    Pair<View, String> p2 = Pair.create((View) tvMovieOverview, "overviewTransit");
//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2);
//                    context.startActivity(intent, options.toBundle());
//
//                }
//            });

        }
    }


}
