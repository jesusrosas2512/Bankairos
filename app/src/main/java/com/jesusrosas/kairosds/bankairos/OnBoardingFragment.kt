package com.jesusrosas.kairosds.bankairos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jesusrosas.kairosds.bankairos.databinding.OnBoardingFragmentBinding

class OnBoardingFragment : Fragment() {

    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var mBinding: OnBoardingFragmentBinding

    private val viewModel: OnBoardingViewModel by viewModels()

    private val finePermission = PermissionRequester(this,
        Manifest.permission.ACCESS_FINE_LOCATION,
        onRationale = { context?.toast("Show Rationale") },
        onDenied = {
            context?.openAppSettings()
            context?.toast("Denied") })

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
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
            readLocation()
        }

        return mBinding.root
    }

    private fun readLocation(){
        if (isLocationEnabled()) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mFusedLocationProviderClient.lastLocation.addOnCompleteListener{ task ->
                    val location: Location? = task.result
                    if (location == null){
                        context?.toast("Not located")
                    } else {
                        mBinding.tvLocation.text = "Lat: ${location.latitude} Lon: ${location.longitude}"
                    }
                }
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}