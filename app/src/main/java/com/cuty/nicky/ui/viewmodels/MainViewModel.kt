package com.cuty.nicky.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo:RepoImplementation) : ViewModel() {
    val fetchItemList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.dataSource.getItemCartaList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


}