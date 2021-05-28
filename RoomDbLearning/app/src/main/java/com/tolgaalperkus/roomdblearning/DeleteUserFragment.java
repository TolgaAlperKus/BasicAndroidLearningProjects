package com.tolgaalperkus.roomdblearning;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DeleteUserFragment extends Fragment {

    private EditText userIdET;
    private Button deleteBtn;

    CompositeDisposable compositeDisposable;

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
                compositeDisposable = new CompositeDisposable();
                int id = Integer.parseInt(userIdET.getText().toString());
                User user = new User ();
                user.setId(id);
                compositeDisposable = new CompositeDisposable();

                compositeDisposable.add(MainActivity.myAppDatabase.myDao().deleteUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(getActivity(), "User Deleted", Toast.LENGTH_SHORT).show();
                        userIdET.setText("");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });

        return view;
    }
}