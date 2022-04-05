package com.jesusrosas.kairosds.bankairos

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jesusrosas.kairosds.bankairos.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var mBinding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMenuBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(
                setOf(R.id.menuFragment, R.id.addCardFragment),
                view.findViewById<DrawerLayout>(R.id.drawer_layout)
            )
        )

        toolbar.title = getString(R.string.my_accounts)

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.nav_menu, menu)
//    }
}