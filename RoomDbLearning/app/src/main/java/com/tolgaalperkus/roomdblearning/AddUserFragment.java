package com.tolgaalperkus.roomdblearning;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddUserFragment extends Fragment {
    private EditText userIdET,userNameET, userEmailET;
    private Button bnSave;
    CompositeDisposable compositeDisposable;

    public AddUserFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        userIdET = view.findViewById(R.id.txt_user_id);
        userNameET = view.findViewById(R.id.txt_name);
        userEmailET= view.findViewById(R.id.txt_email);
        bnSave=view.findViewById(R.id.bn_save_user);

        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId= Integer.parseInt(userIdET.getText().toString());
                String userName= userNameET.getText().toString();
                String userEmail = userEmailET.getText().toString();
                User user = new User();
                user.setId(userId);
                user.setName(userName);
                user.setEmail(userEmail);
                compositeDisposable = new CompositeDisposable();

                compositeDisposable.add(MainActivity.myAppDatabase.myDao().addUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(getActivity(), "User Added", Toast.LENGTH_SHORT).show();
                        userIdET.setText("");
                        userNameET.setText("");
                        userEmailET.setText("");
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