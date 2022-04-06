package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jesusrosas.kairosds.bankairos.databinding.OnBoardingFragmentBinding

class OnBoardingFragment : Fragment() {

    private lateinit var mBinding: OnBoardingFragmentBinding

    private lateinit var viewModel: OnBoardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = OnBoardingFragmentBinding.inflate(inflater, container, false)

        val textView: TextView = mBinding.tvOnBoard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return mBinding.root
    }

}