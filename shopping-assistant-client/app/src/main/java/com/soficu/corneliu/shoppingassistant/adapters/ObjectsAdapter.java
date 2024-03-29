package com.soficu.corneliu.shoppingassistant.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by corne on 17-May-18.
 */

public abstract class ObjectsAdapter<T> extends BaseAdapter {

    protected Activity mContext;
    protected List<T> mItems;

    public ObjectsAdapter(Activity mContext, List<T> items) {
        this.mContext = mContext;
        this.mItems = items;
    }

    public void clear() {
        this.mItems.clear();
    }

    public void insert(T object) {
        this.mItems.add(object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public abstract long getItemId(int i);

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
