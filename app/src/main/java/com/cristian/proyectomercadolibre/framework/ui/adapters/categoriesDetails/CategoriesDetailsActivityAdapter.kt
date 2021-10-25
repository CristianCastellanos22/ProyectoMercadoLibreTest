package com.cristian.proyectomercadolibre.framework.ui.adapters.categoriesDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ItemListCategoriesDetailsViewBinding
import com.cristian.proyectomercadolibre.framework.ui.core.BaseViewHolder
import com.cristian.proyectomercadolibre.models.Result
import com.cristian.proyectomercadolibre.utils.FormatNumber

class CategoriesDetailsActivityAdapter(
    private val itemList: List<Result>,
    private val onClickListenerCategoriesDetailsCardView: OnClickListenerCategoriesDetailsCardView
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = ItemListCategoriesDetailsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultActivityCategoriesDetailsViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ResultActivityCategoriesDetailsViewHolder -> holder.bind(itemList[position])
        }
    }

    override fun getItemCount(): Int = itemList.size

    private inner class ResultActivityCategoriesDetailsViewHolder(
        val binding: ItemListCategoriesDetailsViewBinding,
        val context: Context
    ) : BaseViewHolder<Result>(binding.root) {
        override fun bind(item: Result) {
            Glide.with(context).load(item.thumbnail).error(R.drawable.ic_baseline_cloud_off_24)
                .centerCrop().into(binding.imgViewCategoriesDetails)
            binding.txtNameProductCategoriesDetails.text = item.title
            binding.txtSellCategoriesDetails.text = "Vendidos: " + item.sold_quantity
            binding.txtPriceCategoriesDetails.text = FormatNumber.formatNumber(item.price.toDouble())
            binding.cardItemsCategoriesDetails.setOnClickListener {
                onClickListenerCategoriesDetailsCardView.onClick(item)
            }
        }

    }
}