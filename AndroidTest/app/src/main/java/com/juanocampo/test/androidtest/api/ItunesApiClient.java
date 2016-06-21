package com.juanocampo.test.androidtest.api;

import com.juanocampo.test.androidtest.model.Feed;
import com.juanocampo.test.androidtest.model.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juanocampo on 6/16/16.
 */
public interface ItunesApiClient {

    @GET("/us/rss/topfreeapplications/limit=20/json")
    Call<ServiceResponse> getApps();
}
