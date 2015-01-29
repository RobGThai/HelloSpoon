package com.robgthai.spoon.hellospoon;

import android.content.Context;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class ResourceLoader {

    private WeakReference<Context> mContext;


    public ResourceLoader(Context context) {
        mContext = new WeakReference<Context>(context.getApplicationContext());
    }

    public Bundle getBundle() {
        Bundle b = new Bundle();

        String name = mContext.get().getString(R.string.app_name);
        b.putString("app_name", name);

        return b;
    }
}
