package com.jesusrosas.kairosds.bankairos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.jesusrosas.kairosds.bankairos.databinding.FragmentBaseFormBinding

class BaseFormFragment : Fragment() {

    private lateinit var mBinding: FragmentBaseFormBinding
    private val viewModel: BaseFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentBaseFormBinding.inflate(layoutInflater)
        mBinding.vm = viewModel

        viewModel.frame.observe(viewLifecycleOwner) {
            // Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            mBinding.btnLogin.text = it
            mBinding.flForm.inflateView(it, viewModel)
        }

        viewModel.tokenAccess.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        mBinding.btnLogin.setOnClickListener {
            val loginData = Login("jrb.2512@gmail.com", "@kairosD5?")
            viewModel.login(loginData)
        }

        return mBinding.root
    }

}