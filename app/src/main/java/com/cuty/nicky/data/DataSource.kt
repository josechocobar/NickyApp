package com.cuty.nicky.data

import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.retrofit.RestrofitInstanceCarta
import com.cuty.nicky.vo.Resource

class DataSource {
    suspend fun getItemCartaList():Resource<List<CartaItem>>{
        return Resource.Success( RestrofitInstanceCarta.webService.getAllItems())
    }

}