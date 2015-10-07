package com.lichblitz.aaoconverter.io;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by lichblitz on 6/10/15.
 */
public class CurrencyResponse {

    @SerializedName(JsonKeys.RATES)
    HashMap<String, String> currencyResult;

    public HashMap<String, String> getCurrencyResult() {
        return currencyResult;
    }
}
