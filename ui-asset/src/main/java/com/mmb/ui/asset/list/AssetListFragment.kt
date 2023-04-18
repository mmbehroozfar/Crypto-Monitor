package com.mmb.ui.asset.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mmb.ui.asset.R
import com.mmb.ui.asset.databinding.FragmentAssetListBinding
import com.mmb.ui.asset.util.autoCleared
import com.mmb.ui.asset.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssetListFragment : Fragment(R.layout.fragment_asset_list), MenuProvider {

    private val viewModel by viewModels<AssetListViewModel>()
    private val binding by viewBinding(FragmentAssetListBinding::bind)
    private var adapter by autoCleared<AssetPagingAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        initListeners()
        initRecyclerView()
        initObservers()
    }

    private fun initListeners() = with(binding) {
        toolbar.addMenuProvider(this@AssetListFragment)
        toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun initRecyclerView() = with(binding) {
        swipeContainer.isEnabled = false
        adapter = AssetPagingAdapter(
            onItemClicked = {
                findNavController().navigate(
                    AssetListFragmentDirections.actionAssetListFragmentToAssetDetailFragment(it)
                )
            },
            onFavoriteClicked = viewModel::onFavoriteAssetClicked,
        )
        assetRv.adapter = adapter
        adapter.addLoadStateListener { loadState ->
            noDataTv.isVisible =
                loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.assets.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
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

    private fun initSearchView(searchItem: MenuItem) {
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                viewModel.onSearchModeChanged(true)
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                viewModel.onSearchModeChanged(false)
                return true
            }

        })
        with(searchItem.actionView as SearchView) {
            queryHint = getString(R.string.search)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.onSearchQueryChanged(newText)
                    return true
                }
            })
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        initSearchView(menu.findItem(R.id.search))
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.favorite -> {
                viewModel.onFavoriteFilterClicked()
                true
            }

            else -> false
        }
    }

}