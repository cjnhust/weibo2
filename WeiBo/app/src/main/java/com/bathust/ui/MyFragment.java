package com.bathust.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bathust on 15-4-24.
 */
public class MyFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = this.getActivity();

        TextView tv = new TextView(context);

        Bundle arc = this.getArguments();

        int tabs=arc.getInt("key");

        tv.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));

        tv.setText("hello actionbar "+tabs);

        return tv;

    }
}
