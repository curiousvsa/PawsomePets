package com.valencio.pawsomepets.popUp

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.valencio.pawsomepets.R

class ImagePopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_pop_up)

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        initaiseViews()
        onClickEvents()
    }

    private fun onClickEvents() {
        val closeBtn = findViewById<ImageView>(R.id.close)
        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun initaiseViews() {
        val imageURL = intent.getStringExtra("imageURL") ?: ""
        val ivDogImage = findViewById<ImageView>(R.id.selectedDog)
        imageURL?.let {
            if (imageURL != "") {
                Picasso.get().load(imageURL).into(ivDogImage)
            }
        }
    }
}