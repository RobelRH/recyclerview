package com.example.recyclertest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    public List<User> userList;
    public List<User> userListFiltered;
    private RecyclerViewClickListener listener;
//    List<User> resultData;

    public RecyclerAdapter(List<User> userList, RecyclerViewClickListener listener) {
        this.userList = userList;
        this.userListFiltered = userList;
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();

                if(charSequence == null || charSequence.length()  == 0) {
                    filterResults.count = userListFiltered.size();
                    filterResults.values = userListFiltered;
                }
                else {
                    String searchChr = charSequence.toString().toLowerCase();

                    List<User> resultData = new ArrayList<>();

                    for (User user : userListFiltered) {
                        if(user.getUsername().toLowerCase().contains(searchChr)) {
                            resultData.add(user);
                        }
                    }

                    filterResults.count = resultData.size();
                    filterResults.values = resultData;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userList = (List<User>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        String name = userList.get(position).getUsername();
        holder.nameTxt.setText(name);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

}
