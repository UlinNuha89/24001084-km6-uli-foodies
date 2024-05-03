package com.lynn.foodies.di

import android.content.SharedPreferences
import com.catnip.firebaseauthexample.data.network.firebase.auth.FirebaseAuthDataSource
import com.catnip.firebaseauthexample.data.network.firebase.auth.FirebaseAuthDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.lynn.foodies.data.datasource.cart.CartDataSource
import com.lynn.foodies.data.datasource.cart.CartDatabaseDataSource
import com.lynn.foodies.data.datasource.catalog.CatalogApiDataSource
import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.datasource.category.CategoryApiDataSource
import com.lynn.foodies.data.datasource.category.CategoryDataSource
import com.lynn.foodies.data.datasource.pref.UserDataSource
import com.lynn.foodies.data.datasource.pref.UserPrefDataSource
import com.lynn.foodies.data.repository.CartRepository
import com.lynn.foodies.data.repository.CartRepositoryImpl
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CatalogRepositoryImpl
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.CategoryRepositoryImpl
import com.lynn.foodies.data.repository.UserRepository
import com.lynn.foodies.data.repository.UserRepositoryImpl
import com.lynn.foodies.data.source.local.database.AppDatabase
import com.lynn.foodies.data.source.local.database.dao.CartDao
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.data.source.local.pref.UserPreferenceImpl
import com.lynn.foodies.data.source.network.services.FoodiesApiService
import com.lynn.foodies.presentation.cart.CartViewModel
import com.lynn.foodies.presentation.checkout.CheckoutViewModel
import com.lynn.foodies.presentation.home.HomeViewModel
import com.lynn.foodies.presentation.detailcatalog.DetailCatalogViewModel
import com.lynn.foodies.presentation.login.LoginViewModel
import com.lynn.foodies.presentation.main.MainViewModel
import com.lynn.foodies.presentation.profile.ProfileViewModel
import com.lynn.foodies.presentation.register.RegisterViewModel
import com.lynn.foodies.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    private val networkModule = module {
        single<FoodiesApiService> { FoodiesApiService.invoke()}
    }
    private val firebaseModule = module {
        single<FirebaseAuth> { FirebaseAuth.getInstance() }
    }
    private val localModule = module {
        single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
        single<CartDao> { get<AppDatabase>().cartDao() }
        single<SharedPreferences> {
            SharedPreferenceUtils.createPreference(
                androidContext(),
                UserPreferenceImpl.PREF_NAME
            )
        }
        single<UserPreference> { UserPreferenceImpl(get()) }
    }

    private val datasource = module {
        single<CartDataSource> { CartDatabaseDataSource(get()) }
        single<CategoryDataSource> { CategoryApiDataSource(get()) }
        single<CatalogDataSource> { CatalogApiDataSource(get()) }
        single<UserDataSource> { UserPrefDataSource(get()) }
    }

    private val repository = module {
        single<CartRepository> { CartRepositoryImpl(get()) }
        single<CategoryRepository> { CategoryRepositoryImpl(get()) }
        single<CatalogRepository> { CatalogRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModel { params ->
            DetailCatalogViewModel(
                extras = params.get(),
                cartRepository = get()
            )
        }
        viewModelOf(::CartViewModel)
        viewModelOf(::CheckoutViewModel)
        viewModelOf(::LoginViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::ProfileViewModel)
        viewModelOf(::RegisterViewModel)
    }

    val modules = listOf<Module>(
        networkModule,
        firebaseModule,
        localModule,
        datasource,
        repository,
        viewModelModule
    )
}