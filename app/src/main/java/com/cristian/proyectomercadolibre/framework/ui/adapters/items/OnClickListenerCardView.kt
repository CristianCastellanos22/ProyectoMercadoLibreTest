package com.cristian.proyectomercadolibre.framework.ui.adapters.items

import com.cristian.proyectomercadolibre.domain.models.Product

interface OnClickListenerCardView {
    fun onClick(items: Product)
}