package com.cuty.nicky.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cuty.nicky.R
import com.cuty.nicky.data.DataSource
import com.cuty.nicky.databinding.FragmentCartaBinding
import com.cuty.nicky.domain.RepoImplementation
import com.cuty.nicky.ui.viewmodels.MainViewModel
import com.cuty.nicky.ui.viewmodels.VMFactory
import com.cuty.nicky.vo.Resource


class Carta : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImplementation(DataSource()))
    }

    //private val viewModel by  activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
     //       AppDatabase.getDatabase(requireActivity().applicationContext)))) }

    private lateinit var binding : FragmentCartaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartaBinding.bind(view)
        setUpObservers()

    }
    private fun setUpObservers(){
        viewModel.fetchItemList.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.info.text = result.data.toString()
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                            requireContext(),
                    "Error  = ${result.exception}",
                    Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

}