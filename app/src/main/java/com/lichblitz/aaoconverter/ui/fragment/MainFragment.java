package com.lichblitz.aaoconverter.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lichblitz.aaoconverter.R;
import com.lichblitz.aaoconverter.domain.ResultCurrency;
import com.lichblitz.aaoconverter.io.CurrencyResponse;
import com.lichblitz.aaoconverter.io.FixerApiAdapter;
import com.lichblitz.aaoconverter.io.JsonKeys;
import com.lichblitz.aaoconverter.ui.adapter.ResultAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lichblitz on 6/10/15.
 */
public class MainFragment extends Fragment implements View.OnClickListener, Callback<CurrencyResponse>{

    private RecyclerView mResultList;
    private Button mButtonSearch;
    private EditText mInput;
    private ResultAdapter mResultAdapter;
    private BarChart mBarChart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mResultAdapter = new ResultAdapter(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mResultList = (RecyclerView)root.findViewById(R.id.result_list);
        mButtonSearch = (Button)root.findViewById(R.id.button_search);
        mInput = (EditText)root.findViewById(R.id.input);
        mButtonSearch.setOnClickListener(this);

        mBarChart = (BarChart)root.findViewById(R.id.chart);



        setupResultList();

        return root;

    }

    /**
     * Configures the RecyclerView
     */
    private void setupResultList() {
        mResultList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mResultList.setAdapter(mResultAdapter);

    }


    @Override
    public void onClick(View v) {
        String usd = mInput.getText().toString();
        if(usd.equals("")){
            Toast.makeText(getActivity(), getString(R.string.no_text_message), Toast.LENGTH_SHORT).show();
            mInput.requestFocus();
        }else{
            FixerApiAdapter.getApiService().getBaseCurrency(getString(R.string.currenry_usd), this);
        }
    }

    @Override
    public void success(CurrencyResponse currencyResponse, Response response) {
        //clean the adapter before adding new data
        mResultAdapter.resetData();
        mResultAdapter.addAll(currencyResponse.getCurrencyResult(), mInput.getText().toString());

        //fill the barchart with data
        populateChart(currencyResponse.getCurrencyResult());
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(), getString(R.string.api_error_message), Toast.LENGTH_SHORT).show();
    }


    /**
     * Creates the barchar with the data
     * @param currencyResult: The hashmap result of the apicall
     */
    private void populateChart(HashMap<String, String> currencyResult) {
        float input = Float.valueOf(mInput.getText().toString());

        ArrayList<BarEntry>  data = new ArrayList<>();
        data.add(new BarEntry(Float.valueOf(currencyResult.get(JsonKeys.BRL)), 0));
        data.add(new BarEntry(Float.valueOf(currencyResult.get(JsonKeys.EUR)),1));
        data.add(new BarEntry(Float.valueOf(currencyResult.get(JsonKeys.GBP)), 2));
        data.add(new BarEntry(Float.valueOf(currencyResult.get(JsonKeys.JPY)),3));

        BarDataSet barDataSet = new BarDataSet(data,getString(R.string.chart_data_label));
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<>();
        labels.add(JsonKeys.BRL);
        labels.add(JsonKeys.EUR);
        labels.add(JsonKeys.BRL);
        labels.add(JsonKeys.GBP);


        BarData barData = new BarData(labels, barDataSet);
        mBarChart.setData(barData);
        mBarChart.setDescription(getString(R.string.char_description));
        mBarChart.setDescriptionPosition(0,0);
        mBarChart.animateY(4000);
        mBarChart.notifyDataSetChanged();
        mBarChart.setVisibility(View.VISIBLE);

    }
}
