package com.soficu.corneliu.shoppingassistant.features;

/**
 * Created by corne on 30-May-18.
 */

public interface IAsyncResponse<T> {
    void processFinish(T output);
}
