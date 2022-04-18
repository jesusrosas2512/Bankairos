package com.jesusrosas.kairosds.bankairos.ui.utils

import android.app.Activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jesusrosas.kairosds.bankairos.R

class SuccessDialog constructor(activity: Activity, msg: String) {

    init {
        MaterialAlertDialogBuilder(activity, R.style.SuccessDialog)
            .setTitle(msg)
            .setView(R.layout.success_dialog)
            .setCancelable(false)
            .setNeutralButton(R.string.dialog_confirm, null)
            .show()
    }

}