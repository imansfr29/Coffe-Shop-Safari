package com.imansafari.coffeeshopp25.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imansafari.coffeeshopp25.Domain.BannerModel
import com.imansafari.coffeeshopp25.Repository.MainRepository


class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }
}