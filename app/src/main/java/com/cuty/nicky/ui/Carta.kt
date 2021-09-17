package com.cuty.nicky.ui

import android.os.Bundle
import android.os.Parcelable
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
import com.cuty.nicky.data.LocarDataSource
import com.cuty.nicky.databinding.FragmentCartaBinding
import com.cuty.nicky.domain.AppDatabase
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CarritoItem
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory
import com.cuty.nicky.vo.Resource
import java.lang.Exception


class Carta : Fragment(), MainAdapter.OnTragoClickListener {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()), LocarDataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))
    }

    //private val viewModel by  activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
    //       AppDatabase.getDatabase(requireActivity().applicationContext)))) }

    private var binding: FragmentCartaBinding? = null
    var loadingDialog: LoadingDialog? = null

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
        setUpButtons()
        val navController = findNavController()

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Parcelable>("key")?.observe(
                viewLifecycleOwner
        ) { result ->
            run {
                Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpButtons() {
        binding!!.buCarrito.setOnClickListener {
            try {
                findNavController().navigate(R.id.carritoDeComprasFragment)
            }catch (e:Exception){
                Toast.makeText(context,"error: ${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun setupRecyclerView() {

        binding!!.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvTragos.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )

    }

    private fun setUpObservers() {
        viewModel.fetchItemList.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    //binding.progressBar.visibility = View.VISIBLE
                    loadingDialog?.startLoadingDialog()
                }
                is Resource.Success -> {
                    //binding.progressBar.visibility = View.GONE
                    //binding.info.text = result.data.toString()

                    loadingDialog?.dissmissDialog()
                    binding!!.rvTragos.adapter = MainAdapter(requireContext(),
                            result.data as List<CartaItem>, this)
                }
                is Resource.Failure -> {
                    //binding.progressBar.visibility = View.GONE
                    loadingDialog?.dissmissDialog()
                    Toast.makeText(
                            requireContext(),
                            "Error  = ${result.exception}",
                            Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //loadingDialog = LoadingDialog(requireActivity())
        //Toast.makeText(requireContext(), "onViewResume", Toast.LENGTH_SHORT).show()


    }

    override fun onTragoClick(cartaItem: CartaItem, position: Int) {
        //Toast.makeText(requireContext(), "Hola mina xd", Toast.LENGTH_SHORT).show()
        //determinar que clase es, si es pizza o no y elegir el fragment a donde va

        //ir a detalles y adherezos luego volver para pedir los adherezos
        if (cartaItem.nombreItemCarta.capitalize().contains("z")) {
            //Toast.makeText(requireContext(), "${cartaItem.nombreItemCarta.contains("z")}", Toast.LENGTH_SHORT).show()
            viewModel.insert(CarritoItem(cartaItem.nombreItemCarta, precio = cartaItem.precio, adherezos = cartaItem.ingredientes, imagen = cartaItem.imagen))
            Toast.makeText(requireContext(), "Agregado al carrito!!", Toast.LENGTH_SHORT).show()

            //findNavController().navigate(R.id.carritoDeComprasFragment)
        } else {

            //Toast.makeText(requireContext(), "${cartaItem.nombreItemCarta.contains("pizza")}", Toast.LENGTH_SHORT).show()
            try {
                findNavController().navigate(R.id.adherezos, bundleOf("pedido" to cartaItem))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }


}