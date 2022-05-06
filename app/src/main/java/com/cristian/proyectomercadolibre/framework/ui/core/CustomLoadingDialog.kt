package com.cristian.proyectomercadolibre.framework.ui.core

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import com.cristian.proyectomercadolibre.databinding.CustomLoadingDialogViewBinding

class CustomLoadingDialog(private val activity: Activity) {
    private lateinit var dialog: Dialog
    private lateinit var binding: CustomLoadingDialogViewBinding

    fun showDialog() {
        binding = CustomLoadingDialogViewBinding.inflate(LayoutInflater.from(activity.applicationContext))
        dialog = Dialog(activity)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun cancelDialog() {
        dialog.cancel()
    }
}
