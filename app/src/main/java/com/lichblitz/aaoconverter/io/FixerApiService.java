package com.lichblitz.aaoconverter.io;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lichblitz on 6/10/15.
 * Interface for connecting to the fixer.io api with retrofit
 */
public interface FixerApiService {

    @GET(ApiConstants.URL_BASE_CURRENCY)
    void getBaseCurrency(@Query(ApiConstants.PARAM_BASE) String value, Callback<CurrencyResponse> callback);
}
