package com.example.chistes.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chistes.*
import com.example.chistes.databinding.ActivityMainBinding
import com.example.chistes.ui.utils.OnclickListener
import com.example.chistes.ui.views.adapters.ChistesAdapter
import com.example.chistes.ui.views.fragments.ChisteCompletoFragment
import com.example.chistes.ui.views.fragments.mViewModel
import com.example.chistes.ui.views.models.Chiste
import com.example.chistes.ui.views.viewModels.FragmentChistesViewModel
import com.example.chistes.ui.views.viewModels.MainViewModel

lateinit var fViewModel: MainViewModel

class MainActivity : AppCompatActivity(), OnclickListener {
    lateinit var mBinding:ActivityMainBinding
    lateinit var mAdapter: ChistesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViewModel()
        setUpAdapter()
        fViewModel.setVisivilityFloating(true)
        showProgressButton(false)
        fViewModel.floatingObserver.observe(this){
            if (it.equals(true)){
               showFloatingButton(true)
            }else{
                showFloatingButton(false)
            }
        }

        mBinding.add.setOnClickListener {
            fViewModel.setVisivilityProgress(true)
            fViewModel.getAllChiste()
            fViewModel.chistesObservable.observe(this){listachistes->
                mAdapter.upDateList(listachistes)
            }
            fViewModel.progressObserver.observe(this){
                if (it.equals(true)){
                    mBinding.progress.visibility=View.VISIBLE
                    mBinding.add.isEnabled=false
                }else{
                    mBinding.progress.visibility=View.INVISIBLE
                    mBinding.add.isEnabled=true
                }
            }
        }
    }

    private fun setUpViewModel(){
        fViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel= ViewModelProvider(this).get(FragmentChistesViewModel::class.java)

    }

    private fun setUpAdapter(){

        mAdapter= ChistesAdapter(this, mutableListOf(),this)
        mBinding.recycler.apply {
            adapter=mAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)

        }

    }

    private fun launchEditFragment(frament: Fragment) {
        val fragmentManager= supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.maincontainer,frament)
        fragmentTransaction.addToBackStack(null)//para regresar a nuestra mainactivity*/
        fragmentTransaction.commit()
       fViewModel.setVisivilityFloating(false)
    }
    override fun onClick(chiste: Chiste) {
        launchEditFragment(ChisteCompletoFragment())
       mViewModel.setChisteSelected(chiste)
    }

    private fun showFloatingButton(boolean: Boolean){

        if (boolean.equals(true)){
            mBinding.add.visibility=View.VISIBLE
        }else{
            mBinding.add.visibility=View.GONE

        }
    }

    private fun showProgressButton(boolean: Boolean){

        if (boolean.equals(true)){
            mBinding.progress.visibility=View.VISIBLE
        }else{
            mBinding.progress.visibility=View.GONE

        }
    }


}