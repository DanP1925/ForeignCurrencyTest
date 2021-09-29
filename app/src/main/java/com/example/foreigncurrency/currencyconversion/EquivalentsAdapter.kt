package com.example.foreigncurrency.currencyconversion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foreigncurrency.R
import com.example.foreigncurrency.data.CurrencyEquivalent

class EquivalentsAdapter(
    private val equivalents: List<CurrencyEquivalent>
) : RecyclerView.Adapter<EquivalentsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currencyTextView: TextView = view.findViewById(R.id.tv_equivalent_currency)
        private val amountTextView: TextView = view.findViewById(R.id.tv_equivalent_amount)
        var equivalent: CurrencyEquivalent? = null

        fun setupEquivalent(equivalent: CurrencyEquivalent) {
            this.equivalent = equivalent
            currencyTextView.text = equivalent.currencySymbol
            amountTextView.text = equivalent.equivalentAmount.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_equivalent, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupEquivalent(equivalents[position])
    }

    override fun getItemCount() = equivalents.size
}