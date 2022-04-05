package com.jesusrosas.kairosds.bankairos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jesusrosas.kairosds.bankairos.databinding.FragmentLoginBinding
import com.jesusrosas.kairosds.bankairos.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var mBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentRegisterBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.tvLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_to_loginFragment)
        }

        mBinding.btnRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuFragment)
        }
    }

}