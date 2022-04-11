package com.jesusrosas.kairosds.bankairos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jesusrosas.kairosds.bankairos.databinding.FragmentBaseFormBinding
import com.jesusrosas.kairosds.bankairos.inflateView
import com.jesusrosas.kairosds.bankairos.ui.login.viewmodel.BaseFormViewModel
import com.jesusrosas.kairosds.bankairos.ui.onboarding.OnBoardingFragmentDirections

class BaseFormFragment : Fragment() {

    private lateinit var mBinding: FragmentBaseFormBinding
    private val viewModel: BaseFormViewModel by viewModels()
    private val safeArgs by navArgs<BaseFormFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentBaseFormBinding.inflate(layoutInflater)
        mBinding.vm = viewModel

        viewModel.changeForm(safeArgs.formType)

        viewModel.frame.observe(viewLifecycleOwner) {
            mBinding.btnLogin.text = it
            mBinding.flForm.inflateView(it, viewModel)
        }

        viewModel.tokenAccess.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            if (it != "error") findNavController().navigate(BaseFormFragmentDirections.toAccountFragment())
        }

        viewModel.isFormValid.observe(viewLifecycleOwner){
            mBinding.btnLogin.isEnabled = it
        }

        mBinding.btnLogin.setOnClickListener {
            viewModel.login()
        }

        return mBinding.root
    }

}