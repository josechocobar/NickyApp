package com.cuty.nicky.ui.viewmodels

import androidx.lifecycle.*
import com.cuty.nicky.data.LocarDataSource
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CarritoItem
import com.cuty.nicky.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo:RepoImplementation,private val localDataSource: LocarDataSource) : ViewModel() {
    val fetchItemList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.dataSource.getItemCartaList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
    val carritoItem : LiveData<List<CarritoItem>> = localDataSource.allCarritoItem.asLiveData()

    fun insert(carritoItem: CarritoItem) = viewModelScope.launch {
        localDataSource.insert(carritoItem)
    }


    suspend fun insertCarritoItem(carritoItem: CarritoItem){
        localDataSource.insert(carritoItem)
    }

    suspend fun deleteCarritoItem(id: Int) = viewModelScope.launch{
        localDataSource.deleteItem(id)
    }


}