package com.lebogang.triwizardkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lebogang.triwizardkotlin.pojo.Houses
import com.lebogang.triwizardkotlin.repo.MyRepository


class HousesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: MyRepository? = MyRepository.getInstance()

    private var mLiveData: MutableLiveData<LiveData<List<Houses?>?>?>? = null

    fun HousesViewModel(application: Application) {
        // The local live data needs to reference the repository live data
        mLiveData = repo!!.getHousesLiveData()
    }

    fun getHousesLiveData(): MutableLiveData<LiveData<List<Houses?>?>?>? {
        return mLiveData
    }

}