package com.tolgaalperkus.javafragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup =(ViewGroup) inflater.inflate(R.layout.fragment_first,container,false);
        TextView textView = viewGroup.findViewById(R.id.first_textview);
        textView.setText("1st Fragment example");

        return viewGroup;
    }
}
