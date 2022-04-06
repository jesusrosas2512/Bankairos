package com.jesusrosas.kairosds.bankairos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.jesusrosas.kairosds.bankairos.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private var mActivity: MainActivity? = null

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
            val dataUser =
                BasicUserParams(mBinding.etEmail.text.toString(), mBinding.etName.text.toString())
            //loginOnApi(dataUser)
            Navigation.findNavController(view).navigate(R.id.menuFragment)
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kairos-trainees-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

/*    private fun loginOnApi(dataUser: BasicUserParams) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).login(dataUser)
            val login = call.
            if (call.isSuccessful) {
                val token = login?.token ?: "Success"
                showMsg(token)
            } else {
                showMsg("Error")
            }
            hideKeyboard()
        }
    }*/

    private fun hideKeyboard(){
        val imm = mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow( view?.windowToken, 0)
    }

    private fun showMsg(msg: String) {
        Snackbar.make(mBinding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}