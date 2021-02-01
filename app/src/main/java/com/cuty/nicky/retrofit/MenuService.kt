package com.cuty.nicky.retrofit

import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.models.CartaItemList
import com.cuty.nicky.retrofit.RestrofitInstanceCarta.URL_BASE
import retrofit2.http.GET

interface MenuService {
    @GET("$URL_BASE")
    suspend fun getAllItems(): List<CartaItem>

    //@GET("$URL_BASE/{item}")
    //suspend fun getItem(@Path("item")nombreItem:String) : Response

}