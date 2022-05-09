package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.proyectomercadolibre.data.remote.CategoriesDetailsApiSourceAdapter
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.data.repository.CategoriesDetailsRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.categoriesDetails.CategoriesDetailsUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.models.ProductData
import kotlinx.coroutines.launch

class CategoriesDetailsViewModel(private val categoriesDetailsUseCase: CategoriesDetailsUseCase) :
    ViewModel() {
    private val _categories = MutableLiveData<ProductData>()
    val categories: LiveData<ProductData> = _categories

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getCategoriesDetails(details: String) {
        viewModelScope.launch {
            when (val response = categoriesDetailsUseCase.getCategoriesDetails(details)) {
                is HandlerResponse.Success -> {
                    response.value.let {
                        _categories.postValue(it)
                    }
                }
                is HandlerResponse.Failure -> {
                    _errors.value = response.exception
                }
            }
        }
    }
}

class CategoriesDetailsViewModelFactory() :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesDetailsViewModel(
            CategoriesDetailsUseCaseAdapter(
                CategoriesDetailsRepositoryAdapter(
                    CategoriesDetailsApiSourceAdapter()
                )
            )
        ) as T
    }
}
