package com.cristian.proyectomercadolibre.framework.ui.adapters.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cristian.proyectomercadolibre.databinding.CategoriesListViewBinding
import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.framework.ui.core.BaseViewHolder

class CategoriesActivityAdapter(
    private val categoriesList: List<Categories>,
    private val onClickListenerCategoriesCardView: OnClickListenerCategoriesCardView,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = CategoriesListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CategoriesActivityViewHolder -> holder.bind(categoriesList[position])
        }
    }

    override fun getItemCount(): Int = categoriesList.size

    private inner class CategoriesActivityViewHolder(
        val binding: CategoriesListViewBinding,
    ) : BaseViewHolder<Categories>(binding.root) {
        override fun bind(item: Categories) {
            binding.btnCat.text = item.name
            binding.cardListView.setOnClickListener {
                onClickListenerCategoriesCardView.onClick(item)
            }
        }
    }
}