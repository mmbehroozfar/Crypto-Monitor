package com.mmb.ui.asset.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mmb.ui.asset.R
import com.mmb.ui.asset.databinding.FragmentAssetDetailBinding
import com.mmb.ui.asset.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssetDetailFragment : Fragment(R.layout.fragment_asset_detail) {

    private val viewModel by viewModels<AssetDetailViewModel>()
    private val binding by viewBinding(FragmentAssetDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() = with(binding) {
        swipeContainer.setOnRefreshListener {
            viewModel.fetchAllData()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showError.collect {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.unable_to_fetch_new_data),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}