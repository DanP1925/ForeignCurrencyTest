package com.example.foreigncurrency.supportedcountries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.Country

class SupportedCountriesFragment : Fragment(R.layout.fragment_supported_countries) {

    lateinit var countriesRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesRecyclerView = view.findViewById(R.id.rv_countries)
        countriesRecyclerView.adapter = CountriesAdapter(listOf(Country("Europe", "EUR")))
        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}