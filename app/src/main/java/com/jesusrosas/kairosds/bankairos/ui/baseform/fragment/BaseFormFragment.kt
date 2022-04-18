package com.jesusrosas.kairosds.bankairos.ui.baseform.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jesusrosas.kairosds.bankairos.ui.utils.LoadingDialog
import com.jesusrosas.kairosds.bankairos.databinding.FragmentBaseFormBinding
import com.jesusrosas.kairosds.bankairos.ui.baseform.viewmodel.BaseFormViewModel

class BaseFormFragment : Fragment() {

    private val viewModel: BaseFormViewModel by viewModels()
    private val safeArgs by navArgs<BaseFormFragmentArgs>()
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBaseFormBinding.inflate(inflater, container, false).let {
        it.vm = viewModel
        it.lifecycleOwner = viewLifecycleOwner
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.changeForm(safeArgs.formType)

        viewModel.showLoading.observe(viewLifecycleOwner) {
            if (it) showLoading() else hideLoading()
        }

        viewModel.tokenAccess.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (it != "error") findNavController().navigate(BaseFormFragmentDirections.toAccountFragment())
        }
    }

    private fun showLoading() = loadingDialog.showDialog()

    private fun hideLoading() = loadingDialog.dismissDialog()

}