package com.jesusrosas.kairosds.bankairos.ui.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jesusrosas.kairosds.bankairos.R
import com.jesusrosas.kairosds.bankairos.databinding.AccountFragmentBinding
import com.jesusrosas.kairosds.bankairos.databinding.OnBoardingFragmentBinding
import com.jesusrosas.kairosds.bankairos.ui.onboarding.OnBoardingViewModel

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


    }

}