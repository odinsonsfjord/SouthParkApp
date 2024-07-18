package com.example.southparkapplication.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.southparkapplication.CharactersData
import com.example.southparkapplication.retrofit.RetroInstance
import com.example.southparkapplication.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<CharactersData>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CharactersData>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(ServiceInterface::class.java)
        val call = retroService.getCharacterList()

        call.enqueue(object : Callback<List<CharactersData>> {
            override fun onFailure(call: Call<List<CharactersData>>, t: Throwable) {
                liveDataList.postValue(null)
                Log.e(TAG, "Network error: ${t.message}")
            }

            override fun onResponse(call: Call<List<CharactersData>>, response: Response<List<CharactersData>>) {
                if(response.isSuccessful) {
                    response.body()?.takeIf { it.isNotEmpty() }?.get(0)?.let { character ->
                        liveDataList.postValue(response.body())
                    } ?: run {
                        Log.e(TAG, "No data found")
                    }
                }else {
                    Log.e(TAG, "Failed get character data ${response.code()}")
                }

                }
        })
    }
}