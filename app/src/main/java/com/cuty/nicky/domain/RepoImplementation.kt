package com.cuty.nicky.domain

import com.cuty.nicky.data.DataSource
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.vo.Resource

class RepoImplementation (val dataSource: DataSource) : IRepo {
    override suspend fun getItemCartaList() : Resource<List<CartaItem>>{
        return dataSource.getItemCartaList()
    }
}