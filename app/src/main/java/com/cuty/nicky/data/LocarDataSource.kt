package com.cuty.nicky.data

import androidx.annotation.WorkerThread
import com.cuty.nicky.domain.AppDatabase
import com.cuty.nicky.domain.CarritoDao
import com.cuty.nicky.models.CarritoItem
import kotlinx.coroutines.flow.Flow

class LocarDataSource(private val appDatabase: AppDatabase) {
    //AppDatabase.getDatabase(requireActivity().applicationContext

    val allCarritoItem : Flow<List<CarritoItem>> = appDatabase.carritoDao().getCarrito()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(carritoItem: CarritoItem){
        appDatabase.carritoDao().insertItem(carritoItem)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteItem(item: CarritoItem){
        appDatabase.carritoDao().deleteItem(item)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteItem(id:Int){
        appDatabase.carritoDao().deleteItem(id)
    }
}