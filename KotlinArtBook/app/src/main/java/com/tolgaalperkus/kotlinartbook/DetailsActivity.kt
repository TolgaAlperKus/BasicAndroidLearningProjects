package com.tolgaalperkus.kotlinartbook

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_details.*
import java.io.ByteArrayOutputStream
import java.time.Year

class DetailsActivity : AppCompatActivity() {

    var selectedPicture : Uri? = null
    var selectedBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        val info = intent.getStringExtra("info")
        if(info.equals("new")){
            details_activity_art_name_edittext.setText("")
            details_activity_painter_name_edittext.setText("")
            details_activity_year_edittext.setText("")
            details_activity_save_button.visibility = View.VISIBLE
            val selectedImageBackground = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.selectimage)
            details_activity_select_image_imageview.setImageBitmap(selectedImageBackground)

        }else{
            details_activity_save_button.visibility = View.INVISIBLE
            val selectedId = intent.getIntExtra("id",1)

            val database = this.openOrCreateDatabase("Arts",Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?",arrayOf(selectedId.toString()))

            val artNameIx = cursor.getColumnIndex("artname")
            val painterNameIx = cursor.getColumnIndex("paintername")
            val yearIx = cursor.getColumnIndex("year")
            val imageIx = cursor.getColumnIndex("image")
            while(cursor.moveToNext())
            {
                details_activity_art_name_edittext.setText(cursor.getString(artNameIx))
                details_activity_painter_name_edittext.setText(cursor.getString(painterNameIx))
                details_activity_year_edittext.setText(cursor.getString(yearIx))

                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                details_activity_select_image_imageview.setImageBitmap(bitmap)

            }
            cursor.close()

        }

    }
    fun save ( view : View) {

        val artName = details_activity_art_name_edittext.text.toString()
        val painterName = details_activity_painter_name_edittext.text.toString()
        val artYear = details_activity_year_edittext.text.toString()
        if(selectedBitmap != null){
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!,300)
            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()
            try {
                val database = this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY, artname VARCHAR,paintername VARCHAR, year VARCHAR, image BLOB)")
                val sqlString = "INSERT INTO arts (artname,paintername,year,image)VALUES(?,?,?,?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,artName)
                statement.bindString(2,painterName)
                statement.bindString(3,artYear)
                statement.bindBlob(4,byteArray)
                statement.execute()

            }catch (e : Exception){
                e.printStackTrace()
            }
                    //finish()
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
    }
    fun makeSmallerBitmap(image : Bitmap, maximumSize : Int) : Bitmap{
        var width = image.width
        var height = image.height
        val bitmapRatio : Double = width.toDouble() / height.toDouble()
        if(bitmapRatio > 1) {
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        }
        else{
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(image,width,height,true)
    }

    fun selectImage(view : View){
        if(ContextCompat.checkSelfPermission
                (this, Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf
            (Manifest.permission.READ_EXTERNAL_STORAGE),1)

        }else{
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentToGallery,2)
        }
    }

    override fun onRequestPermissionsResult
            (requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intentToGallery,2)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data!=null){
            selectedPicture = data.data
            try {
                if(selectedPicture != null){
                    if(Build.VERSION.SDK_INT >= 28){
                        val source = ImageDecoder.createSource(this.contentResolver,selectedPicture!!)
                        selectedBitmap = ImageDecoder.decodeBitmap(source)
                        details_activity_select_image_imageview.setImageBitmap(selectedBitmap)
                    }else {
                        selectedBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,selectedPicture)
                        details_activity_select_image_imageview.setImageBitmap(selectedBitmap)
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}