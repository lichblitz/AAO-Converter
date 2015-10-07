package com.lichblitz.aaoconverter.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lichblitz.aaoconverter.domain.ResultCurrency;
import com.lichblitz.aaoconverter.ui.adapter.holder.ResultViewHolder;

import java.util.ArrayList;

/**
 * Created by ${user} on 6/10/15.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{
    ArrayList<ResultCurrency> currencyList;


    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
