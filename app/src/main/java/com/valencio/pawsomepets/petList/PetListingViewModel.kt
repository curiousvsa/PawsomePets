package com.valencio.pawsomepets.petList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valencio.pawsomepets.models.RecyclerList
import com.valencio.pawsomepets.networkCalls.RetroInstance
import com.valencio.pawsomepets.networkCalls.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetListingViewModel : ViewModel() {

    var recyclerListLiveData: MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)
        }
    }
}