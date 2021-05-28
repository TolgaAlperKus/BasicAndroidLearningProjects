package com.tolgaalperkus.roomdblearning;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUserFragment extends Fragment {

    private EditText userIdET;
    private Button deleteBtn;


    public DeleteUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);
        userIdET = view.findViewById(R.id.delete_user_edittext);
        deleteBtn = view.findViewById(R.id.delete_button);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(userIdET.getText().toString());
                User user = new User ();
                user.setId(id);
                MainActivity.myAppDatabase.myDao().deleteUser(user);
                Toast.makeText(getActivity(), "User Deleted", Toast.LENGTH_SHORT).show();
                userIdET.setText("");
            }
        });

        return view;
    }
}