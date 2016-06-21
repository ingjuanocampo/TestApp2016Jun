package com.juanocampo.test.androidtest.api;

import android.content.Context;

import com.juanocampo.test.androidtest.model.Feed;
import com.juanocampo.test.androidtest.model.ServiceResponse;
import com.juanocampo.test.androidtest.util.GsonConverter;
import com.juanocampo.test.androidtest.util.SharePreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juanocampo on 6/19/16.
 */
public class ItunesApiClientImp implements Itunes{

    private static String urlBase = "https://itunes.apple.com";


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final ItunesApiClient itunesApiClient;
    private final SharePreferenceManager mem;


    public ItunesApiClientImp(Context context) {
        itunesApiClient = retrofit.create(ItunesApiClient.class);
        mem = SharePreferenceManager.getInstance(context);
    }

    @Override
    public void getStoresSync(final ResponsesCalls responsesCalls) {

        final Call<ServiceResponse> request = itunesApiClient.getApps();
        request.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful()) {
                    responsesCalls.onAllStoresArrives(response.body());
                    mem.putInMemory(ItunesCacheImp.RESPONSE_SAVE, GsonConverter.object2StringGson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                responsesCalls.onError("Something when wrong");
            }
        });

    }
}
