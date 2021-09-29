package com.example.foreigncurrency.currencyconversion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.foreigncurrency.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConversionFragment : Fragment(R.layout.fragment_currency_conversion) {

    private val args: CurrencyConversionFragmentArgs by navArgs()
    private lateinit var selectedCurrency: String

    private val currencyConversionViewModel by viewModels<CurrencyConversionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedCurrency = args.selectedCurrency

        activity?.title = getString(R.string.currency_conversion_title) + " - " + selectedCurrency

        currencyConversionViewModel.fetchExchangeRates(selectedCurrency)
    }


}