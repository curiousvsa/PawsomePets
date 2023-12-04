package com.valencio.pawsomepets.petList

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.valencio.pawsomepets.R
import com.valencio.pawsomepets.adapters.RecyclerViewAdapter
import com.valencio.pawsomepets.constrants.GlobalKeys
import com.valencio.pawsomepets.models.PetsDataClass


class PetListingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mPetsList = ArrayList<PetsDataClass>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_listing)
        //setUpFragment()
        initaliseviews()
        getPetsList()
    }

    private fun setUpFragment() {
        val fragment = PetListingFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransition: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransition.replace(android.R.id.content, fragment)
        fragmentTransition.commit()
    }

    private fun initaliseviews() {
        recyclerView = findViewById(R.id.rv_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // function for network call
    private fun getPetsList() {
        val queue = Volley.newRequestQueue(this)

        /** API Hit URL : "https://api.thedogapi.com/v1/breeds?limit=20&page=0"*/
        val url = GlobalKeys().baseURL + GlobalKeys().listingPage
        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                try {
                    val petsItemList: Array<PetsDataClass> =
                        Gson().fromJson(response, Array<PetsDataClass>::class.java)
                    Log.d("petsItemList", petsItemList.toString())

                    petsItemList.let {
                        for (items in petsItemList.indices) {
                            Log.d("items: ", petsItemList[items].name.toString())
                            addDataToList(petsItemList[items])
                        }
                        adapter = RecyclerViewAdapter(this@PetListingActivity,mPetsList)
                        recyclerView.adapter = adapter
                    }

                } catch (e: Exception) {
                    val message =
                        resources.getString(R.string.st_somethingWentWrong)
                    Toast.makeText(
                        applicationContext,
                        message, Toast.LENGTH_SHORT
                    ).show()
                }

            },
            { })
        queue.add(stringReq)
    }

    private fun addDataToList(it: PetsDataClass) {
        mPetsList.add(it)
    }

}