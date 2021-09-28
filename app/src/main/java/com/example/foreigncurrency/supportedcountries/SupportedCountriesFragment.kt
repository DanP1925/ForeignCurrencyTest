package com.example.foreigncurrency.supportedcountries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportedCountriesFragment : Fragment(R.layout.fragment_supported_countries) {

    lateinit var countriesRecyclerView: RecyclerView

    private val supportedCountriesViewModel by viewModels<SupportedCountriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesRecyclerView = view.findViewById(R.id.rv_countries)

        supportedCountriesViewModel.countries.observe(viewLifecycleOwner, { countries ->
            setupCountriesList(countries)
        })

        supportedCountriesViewModel.fetchCountries()
    }

    private fun setupCountriesList(countries: List<Country>) {
        countriesRecyclerView.adapter = CountriesAdapter(countries)
        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}