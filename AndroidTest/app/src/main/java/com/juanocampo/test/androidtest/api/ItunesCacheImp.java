package com.juanocampo.test.androidtest.api;

import android.content.Context;
import android.os.AsyncTask;

import com.juanocampo.test.androidtest.model.Entry;
import com.juanocampo.test.androidtest.model.Feed;
import com.juanocampo.test.androidtest.model.ServiceResponse;
import com.juanocampo.test.androidtest.util.GsonConverter;
import com.juanocampo.test.androidtest.util.SharePreferenceManager;

/**
 * Created by juanocampo on 6/19/16.
 */
public class ItunesCacheImp implements Itunes {

    private final SharePreferenceManager mem;
    public static final String RESPONSE_SAVE = "Response_saved";

    public ItunesCacheImp(Context context) {
        mem = SharePreferenceManager.getInstance(context);
    }

    @Override
    public void getStoresSync(final ResponsesCalls responsesCalls) {
        new AsyncTask<Void, Void, ServiceResponse>() {
            @Override
            protected ServiceResponse doInBackground(Void... params) {
                String jsonToParcer = mem.getStringMemory(RESPONSE_SAVE, null);
                return GsonConverter.gsonToFeed(jsonToParcer);
            }

            @Override
            protected void onPostExecute(ServiceResponse serviceResponse) {
                super.onPostExecute(serviceResponse);
                if (serviceResponse != null) {
                    responsesCalls.onAllStoresArrives(serviceResponse);
                } else {
                    responsesCalls.onError("There is not internet connection neither stored data");
                }
            }
        }.execute();
    }

}
