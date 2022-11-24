package com.example.chistes.ui.views.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chistes.*
import com.example.chistes.databinding.ActivityMainBinding
import com.example.chistes.ui.utils.OnclickListener
import com.example.chistes.ui.views.adapters.ChistesAdapter
import com.example.chistes.ui.views.fragments.ChisteCompletoFragment
import com.example.chistes.ui.views.models.Chiste
import com.example.chistes.ui.views.viewModels.FragmentChistesViewModel
import com.example.chistes.ui.views.viewModels.MainViewModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity(), OnclickListener {
    lateinit var mBinding: ActivityMainBinding
    lateinit var mAdapter: ChistesAdapter
    private val fViewModel: MainViewModel by viewModels()
    private val mViewModel: FragmentChistesViewModel by viewModels()
     var listaguardados:MutableList<Chiste> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //setUpViewModel()
        setUpAdapter()
        fViewModel.setVisivilityFloating(true)
        showProgressButton(false)
        getSharePreferences()


        fViewModel.floatingObserver.observe(this) {
            if (it.equals(true)) {
                showFloatingButton(true)
            } else {
                showFloatingButton(false)
            }
        }


        mBinding.add.setOnClickListener {
            fViewModel.setVisivilityProgress(true)
            fViewModel.getAllChiste()
            fViewModel.chistesObservable.observe(this) { listachistes ->
                mAdapter.upDateList(listachistes)
                putSharePreferences(listachistes)
            }
            fViewModel.progressObserver.observe(this) {
                if (it.equals(true)) {
                    mBinding.progress.visibility = View.VISIBLE
                    mBinding.add.isEnabled = false
                } else {
                    mBinding.progress.visibility = View.INVISIBLE
                    mBinding.add.isEnabled = true
                }
            }

        }
    }

    /* private fun setUpViewModel(){
         fViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
         mViewModel= ViewModelProvider(this).get(FragmentChistesViewModel::class.java)

     }*/

    private fun setUpAdapter() {

        mAdapter = ChistesAdapter(this, mutableListOf(), this)
        mBinding.recycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

    }

    private fun launchEditFragment(frament: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.maincontainer, frament)
        fragmentTransaction.addToBackStack(null)//para regresar a nuestra mainactivity*/
        fragmentTransaction.commit()
        fViewModel.setVisivilityFloating(false)
    }

    override fun onClick(chiste: Chiste) {
        launchEditFragment(ChisteCompletoFragment())
        mViewModel.setChisteSelected(chiste)
    }

    private fun showFloatingButton(boolean: Boolean) {

        if (boolean) {
            mBinding.add.visibility = View.VISIBLE
        } else {
            mBinding.add.visibility = View.GONE

        }
    }

    private fun putSharePreferences(listachistes: MutableList<Chiste>) {

        val sharedPreferences = getSharedPreferences("chistes", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        val serializedUser = Gson().toJson(listachistes)
        myEdit.putString("chisteslista", serializedUser)
        myEdit.apply()

    }
    private fun getSharePreferences(){
        val sharedPreferences = getSharedPreferences("chistes", MODE_PRIVATE)
        val lista_chistes= sharedPreferences.getString("chisteslista","")
        if (!lista_chistes.isNullOrEmpty()){
            // below line is to get the type of our array list.
            val type: Type = object : TypeToken<ArrayList<Chiste?>?>() {}.type
            val gson = Gson()
            listaguardados=gson.fromJson<Chiste>(lista_chistes, type) as MutableList<Chiste>
            fViewModel.currentChiste.addAll(listaguardados)
            mAdapter.upDateList(listaguardados)
        }else{
            mAdapter.upDateList(listaguardados)
        }

    }

    private fun showProgressButton(boolean: Boolean) {

        if (boolean) {
            mBinding.progress.visibility = View.VISIBLE
        } else {
            mBinding.progress.visibility = View.GONE

        }
    }


}