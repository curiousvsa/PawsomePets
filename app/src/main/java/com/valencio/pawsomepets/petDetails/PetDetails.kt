package com.valencio.pawsomepets.petDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.valencio.pawsomepets.R
import com.valencio.pawsomepets.constrants.GlobalKeys
import com.valencio.pawsomepets.models.PetSelected
import com.valencio.pawsomepets.popUp.ImagePopUp
import de.hdodenhof.circleimageview.CircleImageView

class PetDetails : AppCompatActivity() {

    private var imageURL: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details)

        initaliseView()
        clickEvents()
    }

    private fun initaliseView() {
        val selectedPetID = intent.getStringExtra("petRefID") ?: ""
        getPetDetails(selectedPetID)
    }

    private fun clickEvents() {
        val dogImage = findViewById<CircleImageView>(R.id.iv_dogImage)
        dogImage.setOnClickListener {
            val imagePopUpIntent = Intent(this@PetDetails, ImagePopUp::class.java)
            imagePopUpIntent.putExtra("imageURL", imageURL ?: "")
            startActivity(imagePopUpIntent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getPetDetails(selectedPetID: String) {
        selectedPetID?.let {
            val queue = Volley.newRequestQueue(this)
            val url = GlobalKeys().baseURL + GlobalKeys().imageUrl + selectedPetID
            /** Request a string response from the provided URL .*/
            val stringReq = StringRequest(Request.Method.GET, url, { response ->
                try {
                    val petsItemList: PetSelected =
                        Gson().fromJson(response, PetSelected::class.java)
                    Log.d("petsItemList", petsItemList.toString())


                    petsItemList?.let { breeds ->
                        breeds.breeds?.let { data ->
                            val tvTitleName = findViewById<TextView>(R.id.tvTitleName)
                            val tvLifeSpan = findViewById<TextView>(R.id.tvLifeSpan)
                            val tvOrigin = findViewById<TextView>(R.id.tvOrigin)
                            val tvTemperament = findViewById<TextView>(R.id.tvTemperament)
                            val ivDogImage = findViewById<ImageView>(R.id.iv_dogImage)

                            data[0]?.let {
                                tvTitleName.text = data[0].name
                                tvLifeSpan.text = "Life Span:" + data[0].life_span
                                tvOrigin.text = "Origin:" + data[0].origin
                                tvTemperament.text = "Temperament:" + data[0].temperament
                                imageURL = petsItemList.url ?: ""
                                imageURL?.let {
                                    if (url != "") {
                                        Picasso.get().load(imageURL).into(ivDogImage)
                                    }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    val message =
                        resources.getString(R.string.st_somethingWentWrong)
                    Toast.makeText(
                        applicationContext,
                        message, Toast.LENGTH_SHORT
                    ).show()
                }

            }, { })
            queue.add(stringReq)
        }
    }
}