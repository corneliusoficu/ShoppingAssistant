package com.soficu.corneliu.shoppingassistant.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.soficu.corneliu.shoppingassistant.MainActivity;
import com.soficu.corneliu.shoppingassistant.features.ISearchProvider;

/**
 * Created by corne on 17-May-18.
 */

public class BaseFragment extends Fragment {

    protected MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)context;
    }

    @Override
    public void onResume() {
        if(this instanceof ISearchProvider) {
            activity.getSearchMenuItem().setVisible(true);
        } else {
            activity.getSearchMenuItem().setVisible(false);
        }
        super.onResume();
    }

}
