package com.example.foreigncurrency.supportedcountries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.Country

class CountriesAdapter(
    private val countries: List<Country>,
    private val onItemClicked: (String?) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onItemClicked: (String?) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.tv_country_name)
        private val currencyTextView: TextView = view.findViewById(R.id.tv_country_currency)
        var country: Country? = null

        init {
            view.setOnClickListener {
                onItemClicked(country?.currencySymbol)
            }
        }

        fun setupCountry(country: Country) {
            this.country = country
            nameTextView.text = country.countryName
            currencyTextView.text = country.currencySymbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_country, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupCountry(countries[position])
    }

    override fun getItemCount() = countries.size

}