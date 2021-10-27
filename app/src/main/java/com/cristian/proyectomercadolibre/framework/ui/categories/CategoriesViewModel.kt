package com.cristian.proyectomercadolibre.framework.ui.categories

import androidx.lifecycle.*
import com.cristian.proyectomercadolibre.models.Categories
import com.cristian.proyectomercadolibre.models.errors.NetworkException
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

class CategoriesViewModelFactory(private val categoriesUseCase: CategoriesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CategoriesUseCase::class.java).newInstance(categoriesUseCase)
    }

}