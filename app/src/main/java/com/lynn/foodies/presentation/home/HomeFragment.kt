package com.lynn.foodies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.lynn.foodies.R
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.model.Category
import com.lynn.foodies.databinding.FragmentHomeBinding
import com.lynn.foodies.presentation.detailcatalog.DetailCatalogActivity
import com.lynn.foodies.presentation.home.adapter.CatalogAdapter
import com.lynn.foodies.presentation.home.adapter.CategoryAdapter
import com.lynn.foodies.presentation.home.adapter.OnItemClickedListener
import com.lynn.foodies.utils.proceedWhen
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
            //assigning variable in viewmodel, so when change to grid/list data didn't change
            viewModel.catalogName = it.name
            observeGridMode()
        }
    }

    private var catalogAdapter: CatalogAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeader(viewModel.isLoggedIn)
        getCategoryData()
        observeGridMode()
        setClickAction()
    }

    private fun setupHeader(isLoggedIn: Boolean) {
        if (isLoggedIn)
            binding.layoutHeader.tvName.text =
                getString(R.string.text_home_name, viewModel.getUsername())
        else
            binding.layoutHeader.tvName.text = getString(R.string.text_home_name, "You")
    }

    private fun observeGridMode() {
        viewModel.isUsingGridMode.observe(viewLifecycleOwner) { isUsingGridMode ->
            viewModel.setPref(isUsingGridMode)
            getCatalogData(isUsingGridMode, viewModel.catalogName)
            setImageListMode(isUsingGridMode)
        }
    }

    private fun getCategoryData() {
        viewModel.getCategories().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnLoading = {
                    binding.rvCategory.isVisible = false
                    binding.ivMenu.isVisible = false
                    binding.tvTitleMenu.isVisible = false
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                },
                doOnSuccess = {
                    binding.rvCategory.isVisible = true
                    binding.ivMenu.isVisible = true
                    binding.tvTitleMenu.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    it.payload?.let { data -> bindCategoryList(data) }
                },
                doOnError = {
                    binding.rvCategory.isVisible = false
                    binding.ivMenu.isVisible = false
                    binding.tvTitleMenu.isVisible = false
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                }
            )
        }
    }

    private fun getCatalogData(isUsingGrid: Boolean, categoryName: String? = null) {
        viewModel.getCatalogs(categoryName).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnLoading = {
                    binding.rvCatalogList.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutState.pbLoading.isVisible = true
                    binding.ivMenu.isVisible = true
                    binding.tvTitleMenu.isVisible = true
                },
                doOnSuccess = {
                    binding.rvCatalogList.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.ivMenu.isVisible = true
                    binding.tvTitleMenu.isVisible = true
                    it.payload?.let { data -> bindCatalogList(isUsingGrid, data) }
                },
                doOnError = {
                    binding.rvCatalogList.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.ivMenu.isVisible = true
                    binding.tvTitleMenu.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                }
            )
        }
    }

    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun setClickAction() {
        binding.ivMenu.setOnClickListener {
            viewModel.changeListMode()
        }
    }

    private fun setImageListMode(usingGridMode: Boolean) {
        binding.ivMenu.setImageResource(
            if (usingGridMode) R.drawable.ic_list_menu else R.drawable.ic_list_menu_grid
        )
    }

    private fun bindCatalogList(isUsingGrid: Boolean, data: List<Catalog>) {
        val listMode = if (isUsingGrid) CatalogAdapter.MODE_GRID else CatalogAdapter.MODE_LIST
        catalogAdapter = CatalogAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Catalog> {
                override fun onItemClicked(item: Catalog) {
                    navigateToDetail(item)
                }
            })
        binding.rvCatalogList.apply {
            adapter = this@HomeFragment.catalogAdapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        catalogAdapter?.submitData(data)
    }

    private fun navigateToDetail(item: Catalog) {
        DetailCatalogActivity.startActivity(requireContext(), item)
    }

}