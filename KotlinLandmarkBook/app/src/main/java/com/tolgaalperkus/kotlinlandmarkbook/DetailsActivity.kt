package com.tolgaalperkus.kotlinlandmarkbook

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent =intent
        val landmarkName = intent.getStringExtra("name")
        activity_details_landmark_name_textview.text = landmarkName
        /*
        val singleton = Singleton.Selected
        val selectedBitmap = singleton.selectedImage
        activity_details_landmark_imageview.setImageBitmap(selectedBitmap)
        */
        val landmarkImageId = intent.getIntExtra("image",0)
        val selectedBitmap = BitmapFactory.decodeResource(applicationContext.resources,landmarkImageId)
        activity_details_landmark_imageview.setImageBitmap(selectedBitmap)



    }
}