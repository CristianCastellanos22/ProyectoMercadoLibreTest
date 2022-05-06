package com.cristian.proyectomercadolibre.framework.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import com.cristian.proyectomercadolibre.data.remote.ItemsApiSourceAdapter
import com.cristian.proyectomercadolibre.data.repository.ItemsRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.items.ItemUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
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
}

class ItemsViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemsViewModel(
            ItemUseCaseAdapter(
                ItemsRepositoryAdapter(
                    ItemsApiSourceAdapter()
                )
            )
        ) as T
    }

}
