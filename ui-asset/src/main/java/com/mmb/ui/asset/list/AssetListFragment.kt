package com.mmb.ui.asset.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmb.ui.asset.R
import com.mmb.ui.asset.databinding.FragmentAssetListBinding
import com.mmb.ui.asset.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetListFragment : Fragment(R.layout.fragment_asset_list) {

    private val viewModel by viewModels<AssetListViewModel>()
    private val binding by viewBinding(FragmentAssetListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

    }

}