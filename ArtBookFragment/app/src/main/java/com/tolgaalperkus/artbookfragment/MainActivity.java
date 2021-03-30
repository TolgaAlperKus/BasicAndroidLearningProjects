package com.tolgaalperkus.artbookfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase database;
    public static int pathInfo =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.options_menu_add_art_item){
            if(pathInfo == 0){
                ListFragmentDirections.ActionListFragmentToİtemFragment action = ListFragmentDirections.actionListFragmentToİtemFragment();
                action.setInfo(0);
                Navigation.findNavController(MainActivity.this,R.id.fragment2).navigate(action);
            }else{
                NavDirections action = ItemFragmentDirections.actionİtemFragmentSelf();
                Navigation.findNavController(MainActivity.this,R.id.fragment2).navigate(action);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}