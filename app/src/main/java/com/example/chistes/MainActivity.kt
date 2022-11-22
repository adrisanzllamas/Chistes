package com.example.chistes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chistes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnclickListener {
    lateinit var fViewModel: MainViewModel
    lateinit var mBinding:ActivityMainBinding
    lateinit var mAdapter:ChistesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViewModel()
        setUpAdapter()
        mBinding.add.setOnClickListener {
            fViewModel.getAllChiste()
            fViewModel.chistesObservable.observe(this){listachistes->
                mAdapter.upDateList(listachistes)
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
    }

    override fun onClick(chiste: Chiste) {
        launchEditFragment(ChisteCompletoFragment())
       mViewModel.setChisteSelected(chiste)
    }


}