package com.valencio.pawsomepets.petList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valencio.pawsomepets.R
import com.valencio.pawsomepets.adapters.RecyclerViewAdapter
import com.valencio.pawsomepets.models.RecyclerList


class PetListingFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pet_listing, container, false)
        initViewModel(view)
        return view
    }

    private fun initViewModel(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.petListRecyclerView)
        recyclerView?.let {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            val decortion = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(decortion)
            //recyclerAdapter = RecyclerViewAdapter(mPetsList)
            recyclerView.adapter = recyclerAdapter
        }

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(PetListingViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(this, Observer <RecyclerList>{

            if (it != null) {
                recyclerAdapter.setUpdatedData(it.items)
            } else {
                //Toast Msg
            }

        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PetListingFragment()
    }
}