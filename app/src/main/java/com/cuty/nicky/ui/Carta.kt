package com.cuty.nicky.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuty.nicky.R
import com.cuty.nicky.data.DataSource
import com.cuty.nicky.databinding.FragmentCartaBinding
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory
import com.cuty.nicky.vo.Resource


class Carta : Fragment(), MainAdapter.OnTragoClickListener {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()))
    }

    //private val viewModel by  activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
     //       AppDatabase.getDatabase(requireActivity().applicationContext)))) }

    private lateinit var binding : FragmentCartaBinding
    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartaBinding.bind(view)
        loadingDialog = LoadingDialog(requireActivity())
        setupRecyclerView()
        setUpObservers()
    }


    private fun setupRecyclerView() {

        binding.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragos.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )

    }
    private fun setUpObservers(){
        viewModel.fetchItemList.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading ->{
                    //binding.progressBar.visibility = View.VISIBLE
                    loadingDialog.startLoadingDialog()
                }
                is Resource.Success ->{
                    //binding.progressBar.visibility = View.GONE
                    //binding.info.text = result.data.toString()
                    loadingDialog.dissmissDialog()
                    binding.rvTragos.adapter = MainAdapter(requireContext(),
                            result.data as List<CartaItem>, this)
                }
                is Resource.Failure -> {
                    //binding.progressBar.visibility = View.GONE
                    loadingDialog.dissmissDialog()
                    Toast.makeText(
                            requireContext(),
                    "Error  = ${result.exception}",
                    Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

    override fun onTragoClick(drink: CartaItem, position: Int) {
        Toast.makeText(requireContext(),"Hola mina xd",Toast.LENGTH_SHORT).show()
        //ir a detalles y adherezos luego volver para pedir los adherezos
        findNavController().navigate(R.id.action_mainFragment_to_adherezos , bundleOf("pedido" to drink))

    }


}