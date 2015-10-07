package com.lichblitz.aaoconverter.io;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by lichblitz on 6/10/15.
 * Response from the api that is parsed into a Hashmap automaticly by Gson.
 *
 */
public class CurrencyResponse {

    @SerializedName(JsonKeys.RATES)
    HashMap<String, String> currencyResult;

    public HashMap<String, String> getCurrencyResult() {
        return currencyResult;
    }
}
