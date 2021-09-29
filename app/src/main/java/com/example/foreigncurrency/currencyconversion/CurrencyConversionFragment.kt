package com.example.foreigncurrency.currencyconversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.CurrencyEquivalent
import com.example.foreigncurrency.supportedcountries.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConversionFragment : Fragment(R.layout.fragment_currency_conversion) {

    private val args: CurrencyConversionFragmentArgs by navArgs()
    private lateinit var selectedCurrency: String

    lateinit var etAmount: EditText
    lateinit var equivalentsRecyclerView: RecyclerView

    private val currencyConversionViewModel by viewModels<CurrencyConversionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedCurrency = args.selectedCurrency

        activity?.title = getString(R.string.currency_conversion_title) + " - " + selectedCurrency

        etAmount = view.findViewById(R.id.et_amount)
        etAmount.addTextChangedListener(onTextChanged)

        equivalentsRecyclerView = view.findViewById(R.id.rv_equivalents)

        currencyConversionViewModel.equivalents.observe(viewLifecycleOwner, Observer(::setupEquivalentsList))

        currencyConversionViewModel.fetchExchangeRates(selectedCurrency)
    }

    val onTextChanged = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Do Nothing
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (text.isNullOrEmpty()) return
            currencyConversionViewModel.obtainEquivalents(text.toString().toDouble())
        }

        override fun afterTextChanged(p0: Editable?) {
            // Do Nothing
        }
    }

    fun setupEquivalentsList(equivalents: List<CurrencyEquivalent>) {
        equivalentsRecyclerView.adapter = EquivalentsAdapter(equivalents)
        equivalentsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}