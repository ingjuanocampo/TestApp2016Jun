package com.juanocampo.test.androidtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by juanocampo on 6/16/16.
 */
public class Image implements Serializable {

    public class Attributes {

        @SerializedName("height")
        @Expose
        private String height;

        /**
         * @return The height
         */
        public String getHeight() {
            return height;
        }

        /**
         * @param height The height
         */
        public void setHeight(String height) {
            this.height = height;
        }
    }
}
