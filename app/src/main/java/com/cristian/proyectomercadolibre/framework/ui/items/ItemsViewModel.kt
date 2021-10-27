package com.cristian.proyectomercadolibre.framework.ui.items

import androidx.lifecycle.*
import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import kotlinx.coroutines.launch

class ItemsViewModel(private val itemsUseCase: ItemsUseCase): ViewModel() {
    private val _items = MutableLiveData<ResponseData>()
    val items: LiveData<ResponseData> = _items

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getItems(item: String) {
        viewModelScope.launch {
            try {
                _items.postValue(itemsUseCase.getItem(item))
            } catch (e: NetworkException) {
                e.message
                _errors.value = e
            } catch (e: Exception) {
                e.message
                _errors.value = e
            }
        }
    }
    /*fun getItems() {
        viewModelScope.launch {
            try {
                _items.postValue(itemsUseCase.getItem(*//**//*))
            } catch (e: NetworkException) {
                e.message
                _errors.value = e
            } catch (e: Exception) {
                e.message
                _errors.value = e
            }
        }
    }*/
}

class ItemsViewModelFactory(private val itemsUseCase: ItemsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ItemsUseCase::class.java).newInstance(itemsUseCase)
    }

}