package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import androidx.lifecycle.*
import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import kotlinx.coroutines.launch

class CategoriesDetailsViewModel(private val categoriesDetailsUseCase: CategoriesDetailsUseCase): ViewModel() {
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

class CategoriesDetailsViewModelFactory(private val categoriesDetailsUseCase: CategoriesDetailsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CategoriesDetailsUseCase::class.java).newInstance(categoriesDetailsUseCase)
    }

}