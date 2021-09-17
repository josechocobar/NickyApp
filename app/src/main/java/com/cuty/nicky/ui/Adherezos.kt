package com.cuty.nicky.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cuty.nicky.R
import com.cuty.nicky.data.DataSource
import com.cuty.nicky.data.LocarDataSource
import com.cuty.nicky.domain.AppDatabase
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.models.CarritoItem
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.lang.Exception


class Adherezos : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()), LocarDataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))
    }
    private var cartaItem: CartaItem? = null
    private var lista: MutableList<String>? = mutableListOf("Verduras")
    private var item: CarritoItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            cartaItem = bundle.getParcelable<CartaItem>("pedido")!!
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adherezos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Toast.makeText(requireContext(), "$cartaItem", Toast.LENGTH_SHORT).show()
        Log.d("ITEM","el item es: ${cartaItem.toString()}")
        setImage(view)
        onSetCheckBox(view)

        //setear los checkbox

    }

    private fun setImage(view: View) {
        //tomar la imagen y ponerla en glide en el imageview de esta view
        cartaItem?.let {
            Glide.with(requireContext()).load(cartaItem!!.imagen).centerCrop().into(view.findViewById(R.id.iv_adherezos_sandwich))

            //Toast.makeText(requireContext(),item.toString(),Toast.LENGTH_SHORT).show()
            //Log.d("carrito",item.toString())
            fabButtonClickListener(view)

        }
    }

    private fun checkList(view: View) {
        Toast.makeText(requireContext(), "${view.id}", Toast.LENGTH_LONG).show()

    }

    fun onSetCheckBox(view: View) {
        val mayonesa = view.findViewById<CheckBox>(R.id.ch_mayonesa)
        mayonesa.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //Toast.makeText(requireContext(), "${mayonesa.text}", Toast.LENGTH_SHORT).show()
                lista?.add("mayonesa")
            } else {
                //Toast.makeText(requireContext(), "no ${mayonesa.text}", Toast.LENGTH_SHORT).show()
                lista?.remove("mayonesa")
            }

        }

        val savora = view.findViewById<CheckBox>(R.id.ch_savora)
        savora.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("savora")
            } else {
                lista?.remove("savora")
            }
        }
        val ketchup = view.findViewById<CheckBox>(R.id.ch_ketchup)
        ketchup.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("ketchup")
            } else {
                lista?.remove("ketchup")
            }
        }
        val cheddar = view.findViewById<CheckBox>(R.id.ch_cheddar)
        cheddar.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("cheddar")
            } else {
                lista?.remove("cheddar")
            }
        }
        val roquefort = view.findViewById<CheckBox>(R.id.ch_roquefort)
        roquefort.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("roquefort")
            } else {
                lista?.remove("roquefort")
            }
        }
        val chimichurri = view.findViewById<CheckBox>(R.id.ch_chimichurri)
        chimichurri.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("chimichurri")
            } else {
                lista?.remove("chimichurri")
            }
        }
        val salame = view.findViewById<CheckBox>(R.id.ch_salame)
        salame.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("salame")
            } else {
                lista?.remove("salame")
            }
        }
        val apio = view.findViewById<CheckBox>(R.id.ch_apio)
        apio.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("apio")
            } else {
                lista?.remove("apio")
            }
        }
        val aceituna = view.findViewById<CheckBox>(R.id.ch_aceituna)
        aceituna.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("aceituna")
            } else {
                lista?.remove("aceituna")
            }
        }
        val parmesano = view.findViewById<CheckBox>(R.id.ch_parmesano)
        parmesano.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("parmesano")
            } else {
                lista?.remove("parmesano")
            }
        }
        val ajo = view.findViewById<CheckBox>(R.id.ch_ajo)
        ajo.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("ajo")
            } else {
                lista?.remove("ajo")
            }
        }
        val choclo = view.findViewById<CheckBox>(R.id.ch_choclo)
        choclo.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("choclo")
            } else {
                lista?.remove("choclo")
            }
        }
        val morron = view.findViewById<CheckBox>(R.id.ch_morron)
        morron.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("morron")
            } else {
                lista?.remove("morron")
            }
        }
        val sal = view.findViewById<CheckBox>(R.id.ch_sal)
        sal.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("sal")
            } else {
                lista?.remove("sal")
            }
        }
        val verduras = view.findViewById<CheckBox>(R.id.ch_verduras)
        verduras.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lista?.add("Verduras")
            } else {
                lista?.remove("Verduras")
            }
        }


    }

    fun setCarritoItem(cartaItem: CartaItem): CarritoItem? {

        var carritoItem: CarritoItem?

        carritoItem = CarritoItem(nombreCarritoItem = cartaItem.nombreItemCarta, precio = cartaItem.precio, adherezos =  lista.toString(), imagen = cartaItem.imagen)

        return carritoItem
    }

    private fun fabButtonClickListener(view: View) {
        val fabBu = view.findViewById<FloatingActionButton>(R.id.fab_ordenar)
        fabBu.setOnClickListener {

            item = setCarritoItem(cartaItem!!)
            val adh = getAdh(lista!!)
            item?.adherezos = adh
            item?.let { it1 -> viewModel.insert(item!!) }
            Toast.makeText(requireContext(), "Agregado al carrito",Toast.LENGTH_LONG).show()
            findNavController().popBackStack()


            //findNavController().navigate(R.id.carritoDeComprasFragment)
            //en este momento deberia regresar al fragment anterior con el carrito
            //Toast.makeText(requireContext(), "${item}",Toast.LENGTH_SHORT).show()

            //Toast.makeText(requireContext(), "${item?.adherezos}",Toast.LENGTH_SHORT).show()
        }
    }

    fun getAdh(mutableList: MutableList<String>):String{
        var aux = ""
        mutableList.let {
            for (item in mutableList){
                aux = "$aux $item"
            }
        }
        return aux
    }


}