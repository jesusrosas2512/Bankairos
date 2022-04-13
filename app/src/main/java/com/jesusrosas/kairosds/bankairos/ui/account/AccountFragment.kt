package com.jesusrosas.kairosds.bankairos.ui.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jesusrosas.kairosds.bankairos.R
import com.jesusrosas.kairosds.bankairos.data.model.TokenProvider
import com.jesusrosas.kairosds.bankairos.databinding.AccountFragmentBinding

class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = AccountFragmentBinding.inflate(inflater, container, false).let {
        it.vm = viewModel
        it.lifecycleOwner = viewLifecycleOwner
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(
                setOf(R.id.accountFragment),
                view.findViewById<DrawerLayout>(R.id.drawer_layout)
            )
        )

        Log.i("Debug", "${TokenProvider.token.token}")

        viewModel.init()
    }

}