package com.example.a2022_04_24_sadafmackertich__nycschools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.SchoolListDataObject
import com.example.a2022_04_24_sadafmackertich__nycschools.databinding.FragmentSchoolItemBinding

class SchoolRecyclerViewAdapter(
    val values: List<SchoolListDataObject>?
) : RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentSchoolItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        holder.item = item!!
        holder.name.text = item.school_name
        holder.address.text = item.primary_address_line_1
    }

    override fun getItemCount(): Int = values!!.size

    inner class ViewHolder(binding: FragmentSchoolItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val address: TextView = binding.address
        val name: TextView = binding.schoolName
        lateinit var item: SchoolListDataObject

        val click = binding.root.setOnClickListener(
            View.OnClickListener {
                val dbn = item.dbn
                val action = SchoolListFragmentDirections
                    .actionSchoolListFragmentToSchoolDetailFragment(dbn)
                Navigation.findNavController(binding.root)
                    .navigate(action)
            })
    }
}
