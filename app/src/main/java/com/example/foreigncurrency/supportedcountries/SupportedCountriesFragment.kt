package com.example.foreigncurrency.supportedcountries

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportedCountriesFragment : Fragment(R.layout.fragment_supported_countries) {

    private lateinit var countriesRecyclerView: RecyclerView
    private lateinit var loading: ProgressBar

    private val supportedCountriesViewModel by viewModels<SupportedCountriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.supported_countries_title)

        loading = view.findViewById(R.id.loading_countries)
        countriesRecyclerView = view.findViewById(R.id.rv_countries)

        supportedCountriesViewModel.loading.observe(viewLifecycleOwner, ::setupLoading)
        supportedCountriesViewModel.showErrorEvent.observe(
            viewLifecycleOwner,
            EventObserver { showErrorMessage() }
        )
        supportedCountriesViewModel.countries.observe(viewLifecycleOwner, ::setupCountriesList)
    }

    private fun setupLoading(isLoading: Boolean) {
        loading.visibility = if (isLoading) VISIBLE else GONE
    }

    private fun showErrorMessage() {
        ErrorSupportedCountriesDialogFragment().show(
            childFragmentManager,
            ErrorSupportedCountriesDialogFragment.TAG
        )
    }

    private fun setupCountriesList(countries: List<Country>) {
        countriesRecyclerView.adapter = CountriesAdapter(countries, ::goToCurrencyConversion)
        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun goToCurrencyConversion(country: String?) {
        if (country.isNullOrEmpty()) {
            return
        }

        findNavController().navigate(
            SupportedCountriesFragmentDirections.actionSupportedCountriesToCurrencyConversion(
                country
            )
        )
    }

}