package com.jesusrosas.kairosds.bankairos

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import com.jesusrosas.kairosds.bankairos.databinding.LoginLayoutBinding
import com.jesusrosas.kairosds.bankairos.databinding.RegisterLayoutBinding

@BindingAdapter(value = ["inflateView", "vm"], requireAll = true)
fun FrameLayout.inflateView(inflateView: String?, vm: BaseFormViewModel) {
    removeAllViews()
    when (inflateView) {
        "login" -> LoginLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
            this.vm = vm
            lifecycleOwner = this@inflateView.context.lifecycleOwner()
        }
        "registro" -> RegisterLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
            this.vm = vm
            lifecycleOwner = this@inflateView.context.lifecycleOwner()
        }
        else -> {
        }
    }
}

private const val MAX_DEPTH = 20

fun Context.lifecycleOwner(): LifecycleOwner? {
    var curContext = this
    var maxDepth = MAX_DEPTH
    while (maxDepth-- > 0 && curContext !is LifecycleOwner) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return curContext as? LifecycleOwner
}