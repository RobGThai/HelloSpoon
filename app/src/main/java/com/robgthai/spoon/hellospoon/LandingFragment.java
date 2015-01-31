package com.robgthai.spoon.hellospoon;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class LandingFragment extends Fragment {

    private WeakReference<ClickListener> mClickListener;

    public static LandingFragment newInstance() {
        return new LandingFragment();
    }

    public LandingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mClickListener = new WeakReference<ClickListener>((ClickListener)activity);
        } catch (Exception e) {
            throw new ClassCastException("Activity must implement interface: " + ClickListener.class.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
        rootView.findViewById(R.id.txtHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    sendEcho("Hello");
                if(mClickListener != null && mClickListener.get() != null) {
                    mClickListener.get().onHelloClickListener();
                }
            }
        });
        return rootView;
    }

    interface ClickListener {
        public void onHelloClickListener();
    }

}
