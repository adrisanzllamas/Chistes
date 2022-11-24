package com.example.chistes.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.chistes.ui.views.viewModels.FragmentChistesViewModel
import com.example.chistes.databinding.FragmentChisteCompletoBinding
import com.example.chistes.ui.views.activities.MainActivity
import com.example.chistes.ui.views.viewModels.MainViewModel


class ChisteCompletoFragment : Fragment() {
    lateinit var mBindind: FragmentChisteCompletoBinding

    private val mViewModel: FragmentChistesViewModel by activityViewModels()
    private val fViewModel: MainViewModel by activityViewModels()
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
            mBindind.chistecompleto.setText(it.setup)
            mBindind.typechiste.setText("Tipo: "+it.type)
            mBindind.punchline.setText(it.punchline)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        fViewModel.setVisivilityFloating(true)
    }
}

