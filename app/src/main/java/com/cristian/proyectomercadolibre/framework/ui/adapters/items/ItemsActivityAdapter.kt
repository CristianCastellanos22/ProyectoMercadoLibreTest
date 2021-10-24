package com.cristian.proyectomercadolibre.framework.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ItemListResultBinding
import com.cristian.proyectomercadolibre.framework.ui.core.BaseViewHolder
import com.cristian.proyectomercadolibre.models.Result
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ItemsActivityAdapter(
    private val itemList: List<Result>,
    private val onClickListenerCardView: OnClickListenerCardView,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = ItemListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultActivityViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ResultActivityViewHolder -> holder.bind(itemList[position])
        }
    }

    override fun getItemCount(): Int = itemList.size

    private inner class ResultActivityViewHolder(
        val binding: ItemListResultBinding,
        val context: Context
    ) : BaseViewHolder<Result>(binding.root) {
        override fun bind(item: Result) {
            Glide.with(context).load(item.thumbnail).timeout(3000).error(R.drawable.ic_baseline_cloud_off_24)
                .centerCrop().into(binding.imgView)
            binding.txtNameProduct.text = item.title
            binding.txtSell.text =  "vendidos: "+item.sold_quantity.toString()
            binding.txtPrice.text = formatNumber(item.price.toDouble())
            binding.cardItems.setOnClickListener {
                onClickListenerCardView.onClick(item)
            }
        }

    }

    fun formatNumber(number: Double): String {
        val valor: Double = number
        val region = Locale.getDefault()
        val formatoMoneda = NumberFormat.getCurrencyInstance(region)
        return (formatoMoneda.format(valor))
    }
}