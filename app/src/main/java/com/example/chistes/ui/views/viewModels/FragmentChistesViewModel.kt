package com.example.chistes.ui.views.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chistes.ui.views.models.Chiste

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