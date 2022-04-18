package com.jesusrosas.kairosds.bankairos.ui.onboarding

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.jesusrosas.kairosds.bankairos.data.model.LocationProvider
import com.jesusrosas.kairosds.bankairos.databinding.OnBoardingFragmentBinding
import com.jesusrosas.kairosds.bankairos.ui.utils.openAppSettings
import com.jesusrosas.kairosds.bankairos.ui.utils.toast
import java.util.*

class OnBoardingFragment : Fragment() {

    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

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
    ) = OnBoardingFragmentBinding.inflate(inflater, container, false).let {
        it.vm = viewModel
        it.lifecycleOwner = viewLifecycleOwner
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finePermission.runWithPermission{
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
            readLocation()
        }

        viewModel.goToFormFragment.observe(viewLifecycleOwner){
            findNavController().navigate(OnBoardingFragmentDirections.toBaseFormFragment(it!!))
        }
    }

    private fun readLocation(){
        if (isLocationEnabled()) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mFusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        val locationRequest = LocationRequest.create().apply {
                            interval = 100
                            fastestInterval = 50
                            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                            maxWaitTime = 100
                        }
                        mFusedLocationProviderClient =
                            LocationServices.getFusedLocationProviderClient(requireContext())
                        Looper.myLooper()?.let {
                            mFusedLocationProviderClient.requestLocationUpdates(
                                locationRequest, locationCallback, it
                            )
                        }
                    } else {
                        viewModel.setLocation(getCityName(location))
                    }
                }
            }
        } else context?.toast("Habilite ubicación")
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            viewModel.setLocation(getCityName(lastLocation))
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getCityName(location: Location):String{
        val geoCoder = Geocoder(context, Locale.getDefault())
        val address = geoCoder.getFromLocation(location.latitude,location.longitude,3)

        val cityName = address[0].locality
        val countryName = address[0].countryName

        val cityAndCountry = "$cityName, $countryName"
        LocationProvider.location = cityAndCountry

        return cityAndCountry
    }

}