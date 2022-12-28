package com.GenrikhsAlexandr.myapplication.presitation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.GenrikhsAlexandr.myapplication.databinding.ListItemCountryBinding

class SpinnerAdapter: RecyclerView.Adapter<SpinnerAdapter.SpinnerViewHolder>() {

    private lateinit var list: ArrayList<String>

    fun submitData(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerViewHolder {
       return SpinnerViewHolder(
           ListItemCountryBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SpinnerViewHolder (private val binding:ListItemCountryBinding):
    RecyclerView.ViewHolder(binding.root)  {
        fun bind(text: String){
            binding.listItemCountry.text = text
        }


    }

}