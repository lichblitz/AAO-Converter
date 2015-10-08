package com.lichblitz.aaoconverter.io;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by lichblitz on 6/10/15.
 * Response from the api that is parsed into a Hashmap automatically by Gson.
 *
 */
public class CurrencyResponse {

    @SerializedName(JsonKeys.RATES)
    HashMap<String, String> currencyResult;

    public HashMap<String, String> getCurrencyResult() {
        return currencyResult;
    }

    public void setCurrencyResult(HashMap<String, String> currencyResult) {
        this.currencyResult = currencyResult;
    }
}
