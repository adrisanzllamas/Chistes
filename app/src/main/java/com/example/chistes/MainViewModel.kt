package com.example.chistes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private var _ChisteLivedata:MutableLiveData<MutableList<Chiste>> = MutableLiveData()
    var chistesObservable:MutableLiveData<MutableList<Chiste>> =_ChisteLivedata
    private var currentChiste:MutableList<Chiste> = mutableListOf()

    fun getAllChiste(){
        val retrofit= ApiClient().getApiClient()
       CoroutineScope(Dispatchers.IO).launch {
           val call=retrofit.create(ApiService::class.java).getChistes()
           if (call.isSuccessful){
               currentChiste.add(call.body()!!)
               _ChisteLivedata.postValue(currentChiste)
           }else{
               //le decimos que hayb un error
           }

       }
    }
}