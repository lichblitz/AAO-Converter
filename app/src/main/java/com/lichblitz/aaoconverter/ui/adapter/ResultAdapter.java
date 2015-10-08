package com.lichblitz.aaoconverter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lichblitz.aaoconverter.R;
import com.lichblitz.aaoconverter.domain.ResultCurrency;
import com.lichblitz.aaoconverter.io.JsonKeys;
import com.lichblitz.aaoconverter.ui.adapter.holder.ResultViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lichblitz on 6/10/15.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{

    private ArrayList<ResultCurrency> currencyList;
    private Context context;
    private Integer input;

    public ResultAdapter(Context context) {
        this.context = context;
        this.currencyList = new ArrayList<>();
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

        ResultCurrency resultCurrency = currencyList.get(position);
        holder.setData(resultCurrency.getName(), resultCurrency.getValue(), input);
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    /**
     * Adds the items from the Hashmap of currency obtained from the fixer.io api call.
     * @param currencyResponse
     * @param input
     */
    public void addAll(HashMap<String, String> currencyResponse, String input) {

        this.input = Integer.valueOf(input);
        currencyList.add(new ResultCurrency("BRL",Double.valueOf(currencyResponse.get(JsonKeys.BRL))));
        currencyList.add(new ResultCurrency("EUR",Double.valueOf(currencyResponse.get(JsonKeys.EUR))));
        currencyList.add(new ResultCurrency("GBP",Double.valueOf(currencyResponse.get(JsonKeys.GBP))));
        currencyList.add(new ResultCurrency("JPY",Double.valueOf(currencyResponse.get(JsonKeys.JPY))));

        notifyItemRangeInserted(getItemCount() - 1, currencyList.size());

    }

    /**
     * Removes all the data from the adapter
     */
    public void resetData() {
        currencyList.clear();
        notifyDataSetChanged();
    }
}
