package com.lichblitz.aaoconverter.ui.fragment;

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

import com.lichblitz.aaoconverter.R;
import com.lichblitz.aaoconverter.io.CurrencyResponse;
import com.lichblitz.aaoconverter.io.FixerApiAdapter;
import com.lichblitz.aaoconverter.ui.adapter.ResultAdapter;

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
        mResultAdapter.addAll(currencyResponse.getCurrencyResult(), mInput.getText().toString());
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(), getString(R.string.api_error_message), Toast.LENGTH_SHORT).show();
    }
}
