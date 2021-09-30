package com.example.foreigncurrency.currencyconversion

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foreigncurrency.R

class ErrorCurrencyConversionDialogFragment(
    private val errorMessage: String?,
    private val onButtonClicked: DialogInterface.OnClickListener
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(
                if (errorMessage.isNullOrEmpty()) {
                    getString(R.string.currency_conversion_error_default)
                } else {
                    getString(R.string.currency_conversion_error_message) + " " + errorMessage
                }
            )
            .setPositiveButton(
                getString(R.string.currency_conversion_positive_button),
                onButtonClicked
            )
            .create()

    companion object {
        const val TAG = "ErrorCurrencyConversionDialogFragment"
    }

}