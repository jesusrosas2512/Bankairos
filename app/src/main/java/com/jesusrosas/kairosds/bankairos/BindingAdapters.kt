package com.jesusrosas.kairosds.bankairos

import android.content.Context
import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jesusrosas.kairosds.bankairos.databinding.LoginLayoutBinding
import com.jesusrosas.kairosds.bankairos.databinding.MyAccountsLayoutBinding
import com.jesusrosas.kairosds.bankairos.databinding.RegisterLayoutBinding
import com.jesusrosas.kairosds.bankairos.ui.account.AccountViewModel
import com.jesusrosas.kairosds.bankairos.ui.account.CardItem
import com.jesusrosas.kairosds.bankairos.ui.login.viewmodel.BaseFormViewModel

@BindingAdapter(value = ["inflateView", "vm"], requireAll = true)
fun FrameLayout.inflateView(inflateView: String?, vm: BaseFormViewModel) {
    removeAllViews()
    when (inflateView) {
        "Ingresar" -> LoginLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
            this.vm = vm
            lifecycleOwner = this@inflateView.context.lifecycleOwner()
        }
        "Registrar" -> RegisterLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
            this.vm = vm
            lifecycleOwner = this@inflateView.context.lifecycleOwner()
        }
        else -> {
        }
    }
}

@BindingAdapter(value = ["inflateViewInfo", "vm"], requireAll = true)
fun FrameLayout.inflateViewInfo(inflateView: String?, vm: AccountViewModel) {
    removeAllViews()
    when (inflateView) {
        "Accounts" -> MyAccountsLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
            this.vm = vm
            lifecycleOwner = this@inflateViewInfo.context.lifecycleOwner()
        }
//        "Registrar" -> RegisterLayoutBinding.inflate(LayoutInflater.from(context), this, true).apply {
//            this.vm = vm
//            lifecycleOwner = this@inflateViewInfo.context.lifecycleOwner()
//        }
        else -> {
        }
    }
}

@BindingAdapter("setVisible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("onTextChanged")
fun TextInputEditText.onTextChanged(onTextChanged: OnTextChanged) {
    doOnTextChanged { text, _, _, _ ->
        onTextChanged.onChanged(text?.toString())
    }
}

@BindingAdapter(
    value = ["errorMessage", "successStyle", "hideErrorIcon", "hideMessage", "errorColorIcon"],
    requireAll = false
)
fun TextInputLayout.setErrorSuccess(
    event: Event<ErrorMessage?>?,
    success: SuccessStyles?,
    hideErrorIcon: Boolean,
    hideMessage: Boolean,
    @ColorInt errorIconColor: Int?
) {
    event?.let { errorMessage ->
        errorMessage.peekContent()?.let {
            isErrorEnabled = true
            errorIconDrawable = if (hideErrorIcon) null else getDrawable(R.drawable.ic_warning)
            if (errorIconColor != null)
                setErrorIconTintList(ColorStateList.valueOf(errorIconColor))
            error = context.getString(it.msg)
            if (hideMessage) {
                getChildAt(1).isVisible = false
            }
        } ?: run {
            error = null
            isErrorEnabled = false
            when (success) {
                SuccessStyles.None -> {
                }
                SuccessStyles.Orange -> {
                    setOrangeStyle()
                }
                else -> {
                    setGreenStyle()
                    setEndIconCheck()
                }
            }
        }
    }
}

@BindingAdapter(
    value = ["cardList", "isLoading"],
    requireAll =
    true
)
fun RecyclerView.setCardAdapter(
    cardList: List<CardItem>?,
    isLoading: Boolean
) {
    if (adapter == null) {
        layoutManager = GridLayoutManager(context, 1)
        adapter = CardAdapter()
    }
    (adapter as? CardAdapter)?.set(isLoading, cardList)
}

interface OnTextChanged {

    fun onChanged(text: String?)
}

fun TextInputLayout.setEndIconCheck() {
    if (editText is TextInputEditText) {
        endIconMode = TextInputLayout.END_ICON_CUSTOM
    }
    endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_check)
}

fun TextInputLayout.setOrangeStyle() {

    val statesStroke = arrayOf(
        intArrayOf(android.R.attr.state_focused),
        intArrayOf()
    )
    val colorsStroke = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryDarkColor)
    )
    setBoxStrokeColorStateList(ColorStateList(statesStroke, colorsStroke))
    val stateIcon = arrayOf(
        intArrayOf(android.R.attr.state_activated),
        intArrayOf(android.R.attr.state_focused),
        intArrayOf()
    )
    val colorIcon = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryDarkColor)
    )

    setEndIconTintList(ColorStateList(stateIcon, colorIcon))
    val stateHint = arrayOf(
        intArrayOf(android.R.attr.state_focused),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    val colorHint = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryDarkColor)
    )

    defaultHintTextColor = ColorStateList(stateHint, colorHint)
    val stateText = arrayOf(
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    val colorText = intArrayOf(
        getColor(context, R.color.primaryDarkColor),
        getColor(context, R.color.primaryDarkColor)
    )

    editText?.setTextColor(ColorStateList(stateText, colorText))
}

fun TextInputLayout.setGreenStyle() {
    val statesStroke = arrayOf(
        intArrayOf(android.R.attr.state_focused),
        intArrayOf()
    )
    val colorsStroke = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.green)
    )

    setBoxStrokeColorStateList(ColorStateList(statesStroke, colorsStroke))
    val stateIcon = arrayOf(
        intArrayOf(android.R.attr.state_activated),
        intArrayOf(android.R.attr.state_focused),
        intArrayOf()
    )
    val colorIcon = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.green)
    )

    setEndIconTintList(ColorStateList(stateIcon, colorIcon))
    val stateHint = arrayOf(
        intArrayOf(android.R.attr.state_focused),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    val colorHint = intArrayOf(
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.primaryLightColor),
        getColor(context, R.color.green)
    )

    defaultHintTextColor = ColorStateList(stateHint, colorHint)
    val stateText = arrayOf(
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    val colorText = intArrayOf(
        getColor(context, R.color.primaryDarkColor),
        getColor(context, R.color.primaryDarkColor)
    )

    editText?.setTextColor(ColorStateList(stateText, colorText))
}

fun View.getDrawable(@DrawableRes drawable: Int) = context.getDrawableFromResource(drawable)

fun Context.getDrawableFromResource(
    @DrawableRes drawable: Int
) = ContextCompat.getDrawable(this, drawable)

private const val MAX_DEPTH = 20

fun Context.lifecycleOwner(): LifecycleOwner? {
    var curContext = this
    var maxDepth = MAX_DEPTH
    while (maxDepth-- > 0 && curContext !is LifecycleOwner) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return curContext as? LifecycleOwner
}