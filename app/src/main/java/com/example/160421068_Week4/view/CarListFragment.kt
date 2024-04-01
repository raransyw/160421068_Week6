package com.example.`160421068_Week4`.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.`160421068_Week4`.R
import com.example.`160421068_Week4`.databinding.FragmentCarListBinding
import com.example.`160421068_Week4`.viewModel.CarViewModel

class CarListFragment : Fragment() {

    private lateinit var viewModel: CarViewModel
    private lateinit var binding: FragmentCarListBinding
    private val carListAdapter  = CarListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        binding.recViewCar.layoutManager = LinearLayoutManager(context)
        binding.recViewCar.adapter = carListAdapter

        Log.d("cek", "masuk")

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recViewCar.visibility =  View.GONE
            binding.txtEror.visibility = View.GONE
            binding.progressBarCar.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.carsLd.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewCar.visibility = View.GONE
                binding.progressBarCar.visibility = View.VISIBLE
            } else {
                binding.recViewCar.visibility = View.VISIBLE
                binding.progressBarCar.visibility = View.GONE
            }
        })
        viewModel.carsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtEror?.visibility = View.VISIBLE
            }
            else {
                binding.txtEror?.visibility = View.GONE
            }
        })
    }
}