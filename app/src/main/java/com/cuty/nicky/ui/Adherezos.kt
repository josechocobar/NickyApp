package com.cuty.nicky.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.cuty.nicky.R
import com.cuty.nicky.data.DataSource
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory


class Adherezos : Fragment() {
    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()))
    }
    private var cartaItem: CartaItem?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            cartaItem = bundle.getParcelable<CartaItem>("pedido")!!
        }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adherezos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(),"$cartaItem",Toast.LENGTH_SHORT).show()
    }


}