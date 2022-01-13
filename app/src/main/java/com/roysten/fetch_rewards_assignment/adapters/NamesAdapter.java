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

import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {

    Context context;
    List<String> namesList;

    public NamesAdapter(Context context, List<String> namesList) {
        this.context = context;
        this.namesList = namesList;
    }

    @NonNull
    @Override
    public NamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View namesView = LayoutInflater.from(context).inflate(R.layout.names_list_item_layout, parent, false);
        return new NamesAdapter.ViewHolder(namesView);
    }

    @Override
    public void onBindViewHolder(@NonNull NamesAdapter.ViewHolder holder, int position) {
        String nameVal = namesList.get(position);
        holder.bind(nameVal);

    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout namesContainer;
        TextView tvNames;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namesContainer = itemView.findViewById(R.id.namesContainer);
            tvNames = itemView.findViewById(R.id.tvNames);
        }

        public void bind(final String name) {
            tvNames.setText(name);
        }
    }
}
