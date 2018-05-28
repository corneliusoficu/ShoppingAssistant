package com.soficu.corneliu.shoppingassistant.listeners;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by corne on 28-May-18.
 */

public abstract class DebouncedListener implements View.OnClickListener {

    private int defaultInterval;
    private long lastTimeChecked = 0;

    public DebouncedListener() {
        this(1000);
    }

    public DebouncedListener(int defaultInterval) {
        this.defaultInterval = defaultInterval;
    }

    @Override
    public void onClick(View view) {
        if(SystemClock.elapsedRealtime() - lastTimeChecked < defaultInterval) {
            return;
        }
        lastTimeChecked = SystemClock.elapsedRealtime();
        performClick(view);
    }

    public abstract void performClick(View view);
}
