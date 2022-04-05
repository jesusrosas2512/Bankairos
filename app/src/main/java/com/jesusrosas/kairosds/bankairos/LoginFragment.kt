package com.jesusrosas.kairosds.bankairos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jesusrosas.kairosds.bankairos.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.tvRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_to_registerFragment)
        }

        mBinding.btnLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuFragment)
        }
    }

}