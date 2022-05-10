package com.cristian.proyectomercadolibre.framework.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import com.cristian.proyectomercadolibre.data.remote.ItemsApiSourceAdapter
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.data.repository.ItemsRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.items.ItemUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.models.ProductData
import kotlinx.coroutines.launch

class ItemsViewModel(private val itemsUseCase: ItemsUseCase): ViewModel() {
    private val _items = MutableLiveData<ProductData>()
    val items: LiveData<ProductData> = _items

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getItems(item: String) {
        viewModelScope.launch {
            when (val response = itemsUseCase.getItem(item)) {
                is HandlerResponse.Success -> {
                    response.value.let {
                        _items.postValue(it)
                    }
                }
                is HandlerResponse.Failure -> {
                    _errors.value = response.exception
                }
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
