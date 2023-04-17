package com.mmb.ui.asset.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmb.ui.asset.R
import com.mmb.ui.asset.databinding.FragmentAssetDetailBinding
import com.mmb.ui.asset.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

}