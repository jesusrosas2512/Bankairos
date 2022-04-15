package com.jesusrosas.kairosds.bankairos

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.app.Dialog

class LoadingDialog constructor(
    activity: Activity
) {
    private var dialog = Dialog(activity)

    init {
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(0))
    }

    fun showDialog(){
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}