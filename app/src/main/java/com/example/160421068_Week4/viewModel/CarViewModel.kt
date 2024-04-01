package com.example.`160421068_Week4`.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.`160421068_Week4`.model.Cars
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarViewModel(application: Application) : AndroidViewModel(application) {
    var carsLd = MutableLiveData<ArrayList<Cars>>()
    val carsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue ?=null

    fun refresh(){
        carsLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/cars/cars.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Cars>>() {}.type
                val result = Gson().fromJson<List<Cars>>(it, sType)
                carsLd.value = result as ArrayList<Cars>?
                loadingLD.value = false

                Log.d("showvolley", result.toString())

            },
            {
                Log.d("showvolley", it.toString())
                loadingLD.value = false
                carsLoadErrorLD.value = false

            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}