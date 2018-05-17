package com.soficu.corneliu.shoppingassistant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soficu.corneliu.shoppingassistant.R;

/**
 * Created by corne on 17-May-18.
 */

public class NearbyStoresFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearby_stores_fragment, container, false);
    }
}
