package com.cuty.nicky.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuty.nicky.domain.RepoImplementation

class VMFactory(private val repo: RepoImplementation) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepoImplementation::class.java).newInstance(repo)
    }
}