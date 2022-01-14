package com.example.kinwaetest.utils

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.kinwaetest.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UtilLoader(private val activity: Activity?) {
    private var builder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(activity!!)
    private var dialog: AlertDialog? = null

    fun show() {
        val inflater = activity!!.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}