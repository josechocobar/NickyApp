package com.cuty.nicky.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuty.nicky.data.LocarDataSource
import com.cuty.nicky.domain.RepoImplementation
import java.lang.IllegalArgumentException

class VMFactory(private val repo: RepoImplementation, private val localDataSource: LocarDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repo, localDataSource) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
        //return modelClass.getConstructor(RepoImplementation::class.java).newInstance(repo)
    }
}