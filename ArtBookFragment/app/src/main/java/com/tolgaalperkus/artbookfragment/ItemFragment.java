package com.tolgaalperkus.artbookfragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.tolgaalperkus.artbookfragment.MainActivity.database;

public class ItemFragment extends Fragment {

    Bitmap selectedImage;
    ImageView imageView;
    EditText artNameEditText;
    EditText painterNameEditText;
    EditText yearEditText;
    Button saveButton;


    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.pathInfo = 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.fragment_item_imageview);
        artNameEditText = view.findViewById(R.id.fragment_item_art_name_edittext);
        painterNameEditText = view.findViewById(R.id.fragment_item_painter_name_edittext);
        yearEditText = view.findViewById(R.id.fragment_item_year_edittext);
        saveButton = view.findViewById(R.id.fragment_item_save_button);

        if(getArguments()!=null){
            int info = ItemFragmentArgs.fromBundle(getArguments()).getInfo();
            if (info == 0){
                //Yeni item ekleme kısmından gelindiyse burası çalışacak
                artNameEditText.setText("");
                painterNameEditText.setText("");
                yearEditText.setText("");
                saveButton.setVisibility(View.VISIBLE);

                Bitmap selectImage = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.selectimage);
                imageView.setImageBitmap(selectImage);
            }else{
                //listeden gelindiyse burası çalışacak
                int artId = ItemFragmentArgs.fromBundle(getArguments()).getArtId();
                saveButton.setVisibility(View.INVISIBLE);
                try {

                    Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?",new String[] {String.valueOf(artId)});

                    int artNameIx = cursor.getColumnIndex("artname");
                    int painterNameIx = cursor.getColumnIndex("paintername");
                    int yearIx = cursor.getColumnIndex("year");
                    int imageIx = cursor.getColumnIndex("image");

                    while (cursor.moveToNext()) {

                        artNameEditText.setText(cursor.getString(artNameIx));
                        painterNameEditText.setText(cursor.getString(painterNameIx));
                        yearEditText.setText(cursor.getString(yearIx));

                        byte[] bytes = cursor.getBlob(imageIx);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        imageView.setImageBitmap(bitmap);
                    }
                    cursor.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(v);
            }
        });

    }

    public void selectImage(View view){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }else{
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == 2) && (resultCode == RESULT_OK) && (data != null)){
            Uri imageData = data.getData();
            try {
                if(Build.VERSION.SDK_INT >=28){
                    ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(),imageData);

                    selectedImage = ImageDecoder.decodeBitmap(source);
                    selectedImage = makeSmallerImage(selectedImage,300);
                }else{
                    selectedImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageData);
                    selectedImage = makeSmallerImage(selectedImage,300);
                }
                imageView.setImageBitmap(selectedImage);


            } catch (IOException e) {
                System.out.println("data null request code = " + requestCode+" result ok degil");
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void save (View view){
        String artName = artNameEditText.getText().toString();
        String painterName = painterNameEditText.getText().toString();
        String year = yearEditText.getText().toString();
        Bitmap smallImage = selectedImage;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray = outputStream.toByteArray();

        try {

            database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY,artname VARCHAR, paintername VARCHAR,year VARCHAR,image BLOB)");
            String sqlString = "INSERT INTO arts (artname,paintername,year,image) VALUES(?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,artName);
            sqLiteStatement.bindString(2,painterName);
            sqLiteStatement.bindString(3,year);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();
            Toast.makeText(getContext().getApplicationContext(),"Kaydedildi!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        NavDirections action = ItemFragmentDirections.actionİtemFragmentToListFragment();
        Navigation.findNavController(view).navigate(action);
    }


    public Bitmap makeSmallerImage(Bitmap image, int maximumSize){
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float)width/(float)height;
        if (bitmapRatio>1){
            width = maximumSize;
            height = (int)(width/bitmapRatio);
        }else{
            height = maximumSize;
            width = (int)(height*bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image,width,height,true);
    }
}