package com.juanocampo.test.androidtest.api;

import com.juanocampo.test.androidtest.model.ServiceResponse;

/**
 * Created by juanocampo on 6/19/16.
 */
public interface ResponsesCalls {

    void onAllStoresArrives(ServiceResponse serviceResponse);

    void onError(String errorMsn);

}
