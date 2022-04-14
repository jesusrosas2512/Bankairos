package com.jesusrosas.kairosds.bankairos.ui.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jesusrosas.kairosds.bankairos.databinding.DropdownItemBinding

class DropDownAdapter(
    context: Context,
    private val objects: List<String>
) : ArrayAdapter<String>(context, 0, objects) {

    override fun getCount() = objects.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.let {
            (convertView.tag as DropdownItemBinding)
        } ?: DropdownItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ).apply {
            root.tag = this
        }
        binding.tvItem.text = objects[position]
        return binding.root
    }
}