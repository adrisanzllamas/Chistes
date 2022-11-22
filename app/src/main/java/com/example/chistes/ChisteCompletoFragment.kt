package com.example.chistes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chistes.databinding.FragmentChisteCompletoBinding

lateinit var mViewModel: FragmentChistesViewModel

class ChisteCompletoFragment : Fragment() {
    lateinit var mBindind: FragmentChisteCompletoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBindind = FragmentChisteCompletoBinding.inflate(inflater, container, false)
        return mBindind.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getChisteSelected()
        mViewModel.chistesCompletosObservable.observe(viewLifecycleOwner) {
            mBindind.idchsite.setText("#" + it.id)

        }
    }
}

