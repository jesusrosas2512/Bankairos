package com.jesusrosas.kairosds.bankairos.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.jesusrosas.kairosds.bankairos.R
import com.jesusrosas.kairosds.bankairos.databinding.AccountFragmentBinding
import com.jesusrosas.kairosds.bankairos.ui.onboarding.OnBoardingFragmentDirections
import com.jesusrosas.kairosds.bankairos.ui.utils.SuccessDialog
import com.jesusrosas.kairosds.bankairos.ui.utils.toast

class AccountFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: AccountViewModel by viewModels()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var successDialog: SuccessDialog

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

        drawerLayout = view.findViewById(R.id.drawer_layout)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(
                setOf(R.id.accountFragment),
                view.findViewById<DrawerLayout>(R.id.drawer_layout)
            )
        )

        val navigationView: NavigationView = view.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        viewModel.changeView("Accounts")

        viewModel.newCardSuccess.observe(viewLifecycleOwner){
            viewModel.changeView("Accounts")
            successDialog = SuccessDialog(requireActivity(), it)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.accounts -> viewModel.changeView("Accounts")
            R.id.cards -> viewModel.changeView("Select")
            R.id.loginFragment -> findNavController().navigate(AccountFragmentDirections.toBaseFormFragment())
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}