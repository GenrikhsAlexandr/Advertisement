package com.GenrikhsAlexandr.myapplication.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.presitation.SpinnerAdapter
import com.GenrikhsAlexandr.myapplication.utils.City

class DialogSpinner {

    fun showDialogSpinner(context: Context, list: ArrayList<String>) {
        val builder = AlertDialog.Builder(context)
        val binding = LayoutInflater.from(context).inflate(R.layout.spinner_dialog, null)
        val adapter: SpinnerAdapter = SpinnerAdapter()
        val rcView = binding.findViewById<RecyclerView>(R.id.rvSpinner)
        val searchView = binding.findViewById<SearchView>(R.id.svSpinner)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
        adapter.submitData(list)
        setSearchView(adapter, list, searchView)
        builder.setView(binding)
        builder.show()
    }

    private fun setSearchView(
        adapter: SpinnerAdapter,
        list: ArrayList<String>,
        searchView: SearchView?,
    ) {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tempList = City.filterListCountry(list,newText)
                adapter.submitData(tempList)
                return true
            }
        })
    }


}