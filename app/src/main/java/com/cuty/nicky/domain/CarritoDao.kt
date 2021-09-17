package com.cuty.nicky.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cuty.nicky.models.CarritoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {

    @Query("SELECT * FROM tabla_carrito ORDER BY id")
    fun getCarrito(): Flow<List<CarritoItem>>

    @Query("SELECT * FROM tabla_carrito")
    fun getCarritoList(): LiveData<List<CarritoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(carritoItem: CarritoItem)

    @Query("DELETE FROM tabla_carrito WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Delete
    suspend fun deleteItem(item:CarritoItem)
    /*
   @Query("DELETE FROM word_table WHERE word = :deleteWord")
    suspend fun deleteWord(deleteWord : String)
     */
}