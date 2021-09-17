package com.cuty.nicky.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuty.nicky.R
import com.cuty.nicky.adapter.CarritoAdapter
import com.cuty.nicky.data.DataSource
import com.cuty.nicky.data.LocarDataSource
import com.cuty.nicky.databinding.FragmentCarritoDeComprasBinding
import com.cuty.nicky.domain.AppDatabase
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CarritoItem
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CarritoDeComprasFragment : Fragment(), CarritoAdapter.OnCarritoItemClickListener {
    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()), LocarDataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))
    }


    private var binding: FragmentCarritoDeComprasBinding? = null
    var loadingDialog: LoadingDialog? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito_de_compras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarritoDeComprasBinding.bind(view)
        loadingDialog = LoadingDialog(requireActivity())
        setupRecyclerView()
        setupObservers()
        setupSendButton()
    }

    private fun setupSendButton() {
        binding!!.buSend.setOnClickListener {
            //obtener la lista e los objetos
            //acomodar toda la lista de los objetos
            val lista = viewModel.carritoItem.value
            var nota:String? = "Hola, quisiera:\n"
            if (lista != null) {
                for (item in lista){
                    //Log.d("ITEM","el item ${item.id}: es ${item}")
                    nota += acomodarStringPedido(item)

                }
                Log.d("ITEM",nota!!)
                enviarAWhatsapp(nota)
            }
        }
    }
    private fun enviarAWhatsapp(nota:String){
        try {
            val i = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=5493875136088&text=${nota}")
            )
            startActivity(i)
        }catch (e:Exception){
            Log.d("ENVIAR",e.message.toString())
        }
    }

    private fun acomodarStringPedido(item: CarritoItem): String{
        try {
            return "*${item.nombreCarritoItem}* \n${item.adherezos} \n"
        }catch (e:Exception){
            Log.d("ERROR",e.message.toString())
        }
        return ""
    }

    private fun setupObservers() {
        viewModel.carritoItem.observe(viewLifecycleOwner, { result ->

            //loadingDialog?.dissmissDialog()
            binding!!.rvCarrito.adapter = CarritoAdapter(requireContext(), result as List<CarritoItem>, this)

        })
    }

    private fun setupRecyclerView() {
        binding!!.rvCarrito.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvCarrito.addItemDecoration(DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
        ))
    }

    fun tostadita() {
        Toast.makeText(context?.applicationContext, "Hola ${viewModel.carritoItem}", Toast.LENGTH_LONG).show()
    }

    override fun onCarritoClick(item: CarritoItem, position: Int) {
        GlobalScope.launch(IO) {
            viewModel.deleteCarritoItem(item.id)
        }
    }


}