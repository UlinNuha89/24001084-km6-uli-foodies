package com.lynn.foodies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lynn.foodies.R
import com.lynn.foodies.presentation.home.adapter.CatalogAdapter
import com.lynn.foodies.data.datasource.catalog.DummyCatalogDataSource
import com.lynn.foodies.data.datasource.category.DummyCategoryDataSource
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CatalogRepositoryImpl
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.CategoryRepositoryImpl
import com.lynn.foodies.databinding.FragmentHomeBinding
import com.lynn.foodies.presentation.home.adapter.CategoryAdapter
import com.lynn.foodies.presentation.home.adapter.OnItemClickedListener
import com.lynn.foodies.utils.GenericViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels {
        val catalogDataSource = DummyCatalogDataSource()
        val catalogRepository: CatalogRepository = CatalogRepositoryImpl(catalogDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository, catalogRepository))
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
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
        setListCategory()
        setClickAction()

    }

    private fun observeGridMode() {
        viewModel.isUsingGridMode.observe(viewLifecycleOwner) { isUsingGridMode ->
            bindCatalogList(isUsingGridMode)
            setImageListMode(isUsingGridMode)
        }
    }

    private fun setListCategory() {
        val data = viewModel.getCategory()
        binding.rvCategory.apply {
            adapter = this@HomeFragment.categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun setClickAction() {
        binding.layoutMenuHeader.ivMenu.setOnClickListener {
            viewModel.changeListMode()
        }
    }

    private fun setImageListMode(usingGridMode: Boolean) {
        binding.layoutMenuHeader.ivMenu.setImageResource(
            if (usingGridMode) R.drawable.ic_list_menu else R.drawable.ic_list_menu_grid
        )
    }

    private fun bindCatalogList(isUsingGrid: Boolean) {
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
        catalogAdapter?.submitData(viewModel.getCatalog())
    }

    private fun navigateToDetail(item: Catalog) {
        //start activity detail catalog using intent
    }

}