package com.example.`160421068_Week4`.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.`160421068_Week4`.databinding.FragmentStudentListBinding
import com.example.`160421068_Week4`.viewModel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding:FragmentStudentListBinding
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = studentListAdapter

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {studentListAdapter.updateStudentList(it) })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it==true){
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            }
            else{
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it==true){
                binding.txtError.visibility = View.VISIBLE
            }
            else{
                binding.txtError.visibility = View.GONE
            }
        })
    }


}