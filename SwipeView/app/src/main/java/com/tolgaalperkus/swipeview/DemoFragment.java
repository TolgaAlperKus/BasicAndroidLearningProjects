package com.tolgaalperkus.swipeview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoFragment extends Fragment {

    private TextView textView;

    public DemoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo,container,false);
        textView = view.findViewById(R.id.txt_display);
        String message = getArguments().getString("message");
        textView.setText(message);

        return view;
    }
}