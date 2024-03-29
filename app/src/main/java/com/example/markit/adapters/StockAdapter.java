package com.example.markit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markit.R;
import com.example.markit.listeners.StockListener;
import com.example.markit.models.Stock;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder> {
    Context context;
    List<Stock> stockList;
    StockListener stockListener;

    public StockAdapter(Context context, List<Stock> stockList, StockListener stockListener) {
        this.context = context;
        this.stockList = stockList;
        this.stockListener = stockListener;

    }

    public void setFilteredList(List<Stock> stockList) {
        this.stockList = stockList;
        notifyDataSetChanged();
    }

    public Stock getItem(int position) {
        return stockList.get(position);
    }


    @NonNull
    @Override
    public StockAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stock_recycler_view_row, parent, false);

        return new StockAdapter.MyViewHolder(view, stockListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.MyViewHolder holder, int position) {

        String symbol = stockList.get(position).ticker;
        String name = stockList.get(position).name;



        holder.textSymbol.setText(symbol);
        holder.textName.setText(name);

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.zoom_out_slide));

    }

    @Override
    public int getItemCount() {

        return stockList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textSymbol;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView, StockListener stockListener) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textSymbol = itemView.findViewById(R.id.textSymbol);
            cardView = itemView.findViewById(R.id.cardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (stockListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            stockListener.onItemClick(position);
                        }
                    }


                }
            });
        }

    }


}
