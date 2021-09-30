package com.example.foreigncurrency.supportedcountries

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foreigncurrency.R

class ErrorSupportedCountriesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.supported_countries_error_message))
            .setPositiveButton(getString(R.string.supported_countries_positive_button)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "ErrorSupportedCountriesDialogFragment"
    }

}