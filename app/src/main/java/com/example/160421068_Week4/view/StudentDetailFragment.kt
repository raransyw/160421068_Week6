package com.example.`160421068_Week4`.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.`160421068_Week4`.R
import com.example.`160421068_Week4`.databinding.FragmentStudentDetailBinding
import com.example.`160421068_Week4`.viewModel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()

        observeViewModel()
    }

    fun observeViewModel(){
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it

            binding.btnUpdate.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        Log.d("Message", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created", R.drawable.baseline_person_2_24)
                    }
            }

            if(it!=null){
                binding.txtId.setText(it.id)
                binding.txtName.setText(it.name)
                binding.txtBirth.setText(it.dob)
                binding.txtPhone.setText(it.phone)
            }
        })
    }

}