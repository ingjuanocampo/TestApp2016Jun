package com.juanocampo.test.androidtest.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.juanocampo.test.androidtest.model.Entry;
import com.juanocampo.test.androidtest.model.Feed;
import com.juanocampo.test.androidtest.model.ServiceResponse;

import java.lang.reflect.Type;

/**
 * Created by juanocampo on 6/19/16.
 */
public class GsonConverter {

    /**
     *
     * @param object
     * @return String JSON of the object send
     */
    public static String object2StringGson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static ServiceResponse gsonToFeed (String data) {
        Gson gson = new Gson();
        Type token = new TypeToken<ServiceResponse>(){}.getType();
        return gson.fromJson(data,token);
    }
}
