package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.proyectomercadolibre.data.remote.CategoriesDetailsApiSourceAdapter
import com.cristian.proyectomercadolibre.data.repository.CategoriesDetailsRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.categoriesDetails.CategoriesDetailsUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import kotlinx.coroutines.launch

class CategoriesDetailsViewModel(private val categoriesDetailsUseCase: CategoriesDetailsUseCase) :
    ViewModel() {
    private val _categories = MutableLiveData<ResponseData>()
    val categories: LiveData<ResponseData> = _categories

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getCategoriesDetails(details: String) {
        viewModelScope.launch {
            try {
                _categories.postValue(categoriesDetailsUseCase.getCategoriesDetails(details))
            } catch (e: NetworkException) {
                _errors.value = e
            } catch (e: Exception) {
                _errors.value = e
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
