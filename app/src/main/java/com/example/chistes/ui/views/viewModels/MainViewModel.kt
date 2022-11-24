package com.example.chistes.ui.views.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chistes.ui.views.models.Chiste
import com.example.chistes.data.services.ApiClient
import com.example.chistes.data.services.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private var _ChisteLivedata:MutableLiveData<MutableList<Chiste>> = MutableLiveData()
    var chistesObservable:MutableLiveData<MutableList<Chiste>> =_ChisteLivedata
     var currentChiste:MutableList<Chiste> = mutableListOf()


    private var _ProgressLiveData:MutableLiveData<Boolean> = MutableLiveData()
    var progressObserver:MutableLiveData<Boolean> =_ProgressLiveData

    private var _FloatingLiveData:MutableLiveData<Boolean> = MutableLiveData()
    var floatingObserver:MutableLiveData<Boolean> =_FloatingLiveData

    fun getAllChiste(){
        val retrofit= ApiClient().getApiClient()
       viewModelScope.launch {
           val call=retrofit.create(ApiService::class.java).getChistes()
           if (call.isSuccessful){
               if (!(currentChiste.equals(call.body()))){
                   currentChiste.add(call.body()!!)
                   _ChisteLivedata.postValue(currentChiste)
                   _ProgressLiveData.postValue(false)
               }

           }else{
               _ProgressLiveData.postValue(true)
           }
       }
    }

    fun setVisivilityProgress(boolean: Boolean){
        _ProgressLiveData.postValue(boolean)
    }

    fun setVisivilityFloating(boolean: Boolean){
        _FloatingLiveData.postValue(boolean)
    }

}