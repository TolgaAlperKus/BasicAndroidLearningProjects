package com.tolgaalperkus.artbookfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>{

    private ArrayList<ArtItems> artItemsArrayList;

    public RecyclerViewAdapter (ArrayList<ArtItems> artItemsArrayList){
        this.artItemsArrayList = artItemsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new RowHolder(view);
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RowHolder holder, int position) {
        TextView rowTextView = holder.itemView.findViewById(R.id.recycler_row_textview);
        rowTextView.setText(artItemsArrayList.get(position).getArtName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListFragmentDirections.ActionListFragmentToİtemFragment action = ListFragmentDirections.actionListFragmentToİtemFragment();
                action.setInfo(1);
                action.setArtId(artItemsArrayList.get(position).getId());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artItemsArrayList.size();
    }
}
