package com.jesusrosas.kairosds.bankairos

import android.Manifest
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.fragment.app.viewModels
import com.jesusrosas.kairosds.bankairos.databinding.OnBoardingFragmentBinding

class OnBoardingFragment : Fragment() {

    private lateinit var mBinding: OnBoardingFragmentBinding

    private val viewModel: OnBoardingViewModel by viewModels()

    private val finePermission = PermissionRequester(this, Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = OnBoardingFragmentBinding.inflate(inflater, container, false)

        val textView: TextView = mBinding.tvWelcome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        finePermission.runWithPermission{
            Toast.makeText(context, "Granted", Toast.LENGTH_SHORT).show()
        }

        return mBinding.root
    }

}

class PermissionRequester(fragment: Fragment, private val permission: String) {

    private var onGranted: () -> Unit = {}

    private val permissionLauncher = fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        when {
            isGranted -> onGranted()
        }
    }

    fun runWithPermission(body: () -> Unit){
        onGranted = body
        permissionLauncher.launch(permission)
    }

}