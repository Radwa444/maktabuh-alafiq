package com.example.maktabuhalafiq.ui.common.views


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import com.example.maktabuhalafiq.R


class ProgressDialog {
    companion object {
        fun createProgressDialog(context: Context): Dialog {
            val dialog = Dialog(context)
            val inflate =
                LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }

        fun showDownloadCompleteDialog(context: Context) {
            val dialogView =
                LayoutInflater.from(context).inflate(R.layout.dialog_download_complete, null)
            val dialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            dialogView.findViewById<Button>(R.id.btnOk).setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}