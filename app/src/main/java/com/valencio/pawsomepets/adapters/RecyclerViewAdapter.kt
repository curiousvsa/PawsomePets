package com.valencio.pawsomepets.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.valencio.pawsomepets.R
import com.valencio.pawsomepets.models.PetsDataClass
import com.valencio.pawsomepets.models.RecyclerData
import com.valencio.pawsomepets.petDetails.PetDetails

class RecyclerViewAdapter(
    private val context: Context,
    mPetsList: ArrayList<PetsDataClass>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = mPetsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemData = items[position]


        if (position % 2 == 0)
            holder.clOuterLayer.background = context.resources.getDrawable(R.drawable.ic_rounded_corner_primary)
        else
            holder.clOuterLayer.background = context.resources.getDrawable(R.drawable.ic_rounded_corner_secondary)

        holder.clOuterLayer.setOnClickListener {
            Log.d("ItemClicked", "${itemData.name} , Position : $position")
            openDetailsScreen(itemData)
        }

        holder.tvTitleName.text = itemData.name
        holder.tvLifeSpan.text = "Life Span:" + itemData.life_span
        holder.tvOrigin.text = "Origin:" + itemData.origin
        holder.tvTemperament.text = "Temperament:" + itemData.temperament

    }

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        notifyDataSetChanged()
    }

    private fun openDetailsScreen(data: PetsDataClass) {
        data.let {
            val petDetailsIntent = Intent(context, PetDetails::class.java)
            petDetailsIntent.putExtra("petRefID", data.reference_image_id ?: "")
            context.startActivity(petDetailsIntent)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clOuterLayer = view.findViewById<ConstraintLayout>(R.id.cl_outerLayer)
        val tvTitleName = view.findViewById<TextView>(R.id.item_title_name)
        val tvLifeSpan = view.findViewById<TextView>(R.id.item_lifeSpan)
        val tvOrigin = view.findViewById<TextView>(R.id.item_origin)
        val tvTemperament = view.findViewById<TextView>(R.id.item_temperament)
    }
}