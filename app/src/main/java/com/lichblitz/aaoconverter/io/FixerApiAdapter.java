package com.lichblitz.aaoconverter.io;

import retrofit.RestAdapter;

/**
 * Created by lichblitz on 6/10/15.
 */
public class FixerApiAdapter {

    public static FixerApiService API_SERVICE;

    public static FixerApiService getApiService(){
        if(API_SERVICE == null){
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();

            API_SERVICE = adapter.create(FixerApiService.class);
        }

        return API_SERVICE;
    }

    public static void setApiService(FixerApiService apiService) {
        API_SERVICE = apiService;
    }
}
