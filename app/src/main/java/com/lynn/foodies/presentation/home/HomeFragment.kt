package com.lynn.foodies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lynn.foodies.R
import com.lynn.foodies.data.datasource.catalog.CatalogApiDataSource
import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.datasource.category.CategoryApiDataSource
import com.lynn.foodies.data.datasource.category.CategoryDataSource
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.model.Category
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CatalogRepositoryImpl
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.CategoryRepositoryImpl
import com.lynn.foodies.data.source.network.services.FoodiesApiService
import com.lynn.foodies.databinding.FragmentHomeBinding
import com.lynn.foodies.presentation.detailcatalog.DetailCatalogActivity
import com.lynn.foodies.presentation.home.adapter.CatalogAdapter
import com.lynn.foodies.presentation.home.adapter.CategoryAdapter
import com.lynn.foodies.presentation.home.adapter.OnItemClickedListener
import com.lynn.foodies.utils.GenericViewModelFactory
import com.lynn.foodies.utils.proceedWhen

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels {
        val service = FoodiesApiService.invoke()
        val catalogDataSource: CatalogDataSource = CatalogApiDataSource(service)
        val catalogRepository: CatalogRepository = CatalogRepositoryImpl(catalogDataSource)
        val categoryDataSource: CategoryDataSource = CategoryApiDataSource(service)
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(
            HomeViewModel(
                categoryRepository,
                catalogRepository,
                requireContext()
            )
        )
    }


    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
            //assigning variable in viewmodel, so when change grid/list data didn't change
            observeGridMode()
            viewModel.catalogName = it.name
            getCatalogData(viewModel.isGridMode, viewModel.catalogName)
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
        observeGridMode()
        getCategoryData()
        getCatalogData(viewModel.isGridMode)
        setClickAction()
    }

    private fun observeGridMode() {
        viewModel.isUsingGridMode.observe(viewLifecycleOwner) { isUsingGridMode ->
            viewModel.setPref(requireContext(), isUsingGridMode)
            viewModel.isGridMode = isUsingGridMode
            getCatalogData(isUsingGridMode, viewModel.catalogName)
            setImageListMode(isUsingGridMode)
        }
    }

    private fun getCategoryData() {
        viewModel.getCategories().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindCategoryList(data) }
                }
            )
        }
    }

    private fun getCatalogData(isUsingGrid: Boolean, categoryName: String? = null) {
        viewModel.getCatalogs(categoryName).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindCatalogList(isUsingGrid, data) }
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