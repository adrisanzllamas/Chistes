package com.example.chistes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

class FragmentChistesViewModel:ViewModel() {


    private var _ChisteCompletoLivedata: MutableLiveData<Chiste> = MutableLiveData()
    var chistesCompletosObservable: MutableLiveData<Chiste> =_ChisteCompletoLivedata

    fun getChisteSelected(): LiveData<Chiste> {
        return chistesCompletosObservable
    }
    fun setChisteSelected(chiste: Chiste){
        _ChisteCompletoLivedata.value=chiste
    }
}