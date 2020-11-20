package com.example.casestudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * MyAdapter job is to put data into each row of the listview
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.WordViewHolder> {
    String[] data;
    LayoutInflater layoutInflater;
    OnCityListener mOnCityListener;
    public MyAdapter(Context context, String[] languagesData, OnCityListener onCityListener) {
        data = languagesData;
        layoutInflater = LayoutInflater.from(context);
        this.mOnCityListener= onCityListener;
    }

    /**
     * 2. yash
     * onCreateViewHolder  job is to buy wooden planks
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = layoutInflater.inflate(R.layout.row_listview,parent,false);
        return new WordViewHolder(rowView, mOnCityListener);
    }

    /**
     * 3. jyothsana
     * onBindViewHolder job is write data on the planks
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.WordViewHolder holder, int position) {
        holder.titleTextView.setText(data[position]);
        //holder.titleTextView.onclick
    }

    /**
     * 1. sandeep
     * job is to keep the count of no of data items in the dataset
     * @return
     */
    @Override
    public int getItemCount() {
        return data.length;
    }


    /**
     * manasa
     * WordViewHolder job is to hold the recycled stock and new stock of wooden planks
     */
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public  TextView titleTextView;
        OnCityListener onCityListener;
        public WordViewHolder(@NonNull View itemView,OnCityListener onCityListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textviewRow);
            this.onCityListener= onCityListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCityListener.onCityClick(getAdapterPosition());
        }
    }
    public interface OnCityListener{
        void onCityClick(int position);
    }
}
