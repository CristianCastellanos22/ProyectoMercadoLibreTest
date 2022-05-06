package com.cristian.proyectomercadolibre.framework.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import com.cristian.proyectomercadolibre.data.remote.CategoriesApiSourceAdapter
import com.cristian.proyectomercadolibre.data.repository.CategoriesRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.categories.CategoriesUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import kotlinx.coroutines.launch

class CategoriesViewModel(private val categoriesUseCase: CategoriesUseCase): ViewModel() {
    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> = _categories

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.postValue(categoriesUseCase.getCategories())
            } catch (e: NetworkException) {
                _errors.value = e
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

}

class CategoriesViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(
            CategoriesUseCaseAdapter(
                CategoriesRepositoryAdapter(
                    CategoriesApiSourceAdapter()
                )
            )
        ) as T
    }
}
