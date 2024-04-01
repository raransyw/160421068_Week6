package com.example.`160421068_Week4`.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.`160421068_Week4`.model.Cars
import com.example.`160421068_Week4`.databinding.CarListItemBinding

class CarListAdapter(val carList: ArrayList<Cars>)
    :RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    class CarViewHolder(var binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtIdCar.text = carList[position].id
        holder.binding.txtMake.text = carList[position].make
        holder.binding.txtModel.text = carList[position].model

    }

    fun updateCarList(newCarList: ArrayList<Cars>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }



}