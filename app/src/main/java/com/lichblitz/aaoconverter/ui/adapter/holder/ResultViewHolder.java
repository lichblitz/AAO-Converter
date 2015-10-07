package com.lichblitz.aaoconverter.ui.adapter.holder;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lichblitz.aaoconverter.R;
import com.lichblitz.aaoconverter.app.AppConverter;
import com.lichblitz.aaoconverter.io.JsonKeys;

import java.math.BigDecimal;

/**
 * Created by lichblitz on 6/10/15.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder{

    private TextView mCurrencyInitial;
    private TextView mCurrencyName;
    private TextView mCurrencyConvertValue;

    public ResultViewHolder(View itemView) {
        super(itemView);
        mCurrencyInitial = (TextView) itemView.findViewById(R.id.text_currency_initials);
        mCurrencyName = (TextView) itemView.findViewById(R.id.text_currency_name);
        mCurrencyConvertValue = (TextView) itemView.findViewById(R.id.text_currency_result);

    }


    /**
     * Adds the data to the item view
     * @param currencyName
     * @param value
     * @param input : the value to be converted
     */
    public void setData(String currencyName, double value, Integer input){
        switch (currencyName){
            case JsonKeys.BRL:
                setDrawable(R.drawable.icon_brl);
                setName(AppConverter.getStringFromId(R.string.brl));
                setInitial(AppConverter.getStringFromId(R.string.currency_brl));
                break;
            case JsonKeys.EUR:
                setDrawable(R.drawable.icon_eur);
                setName(AppConverter.getStringFromId(R.string.eur));
                setInitial(AppConverter.getStringFromId(R.string.currency_eur));
                break;
            case JsonKeys.GBP:
                setDrawable(R.drawable.icon_gbp);
                setName(AppConverter.getStringFromId(R.string.gbp));
                setInitial(AppConverter.getStringFromId(R.string.currency_gbp));
                break;
            case JsonKeys.JPY:
                setDrawable(R.drawable.icon_jpy);
                setName(AppConverter.getStringFromId(R.string.jpy));
                setInitial(AppConverter.getStringFromId(R.string.currency_jpy));
                break;
        }

        setCurrencyConverted(value, input);
    }


    private void setCurrencyConverted(double value, Integer input){
        //removing exponential
        String result = new BigDecimal(String.format("%f",value*input)).stripTrailingZeros().toPlainString();
        mCurrencyConvertValue.setText(result);
    }

    private void setDrawable(@DrawableRes int drawable){
        mCurrencyInitial.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);

    }

    private void setName(String name){
        mCurrencyName.setText(name);
    }

    private void setInitial(String initial){
        mCurrencyInitial.setText(initial);
    }


}
