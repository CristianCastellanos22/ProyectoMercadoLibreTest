package com.cristian.proyectomercadolibre.framework.ui.itemsDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityItemsDetailsBinding
import com.cristian.proyectomercadolibre.domain.models.Product
import com.cristian.proyectomercadolibre.framework.delegate.viewBinding
import com.cristian.proyectomercadolibre.utils.FormatNumber
import com.cristian.proyectomercadolibre.utils.KEY_RESULT

class ItemsDetailsActivity : AppCompatActivity() {
    private val binding: ActivityItemsDetailsBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbarItemsDetails.edtSearch.visibility = View.GONE
        getData()
        clickBtnQuestion()
        clickBtnCall()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        with(binding) {
            intent.getParcelableExtra<Product>(KEY_RESULT).let {
                if (it != null) {
                    txtNameDetails.text = it.title
                    Glide.with(applicationContext).load(it.thumbnail)
                        .error(R.drawable.ic_baseline_cloud_off_24)
                        .fitCenter()
                        .into(imgProfileDetails)
                    txtPriceDetails.text = FormatNumber.formatNumber(it.price.toDouble())
                    addressSeller.text = "${it.sellerAddress.city.name} ${it.sellerAddress.state.name}"
                }


            }
        }
    }

    private fun clickBtnQuestion() {
        binding.btnQuestion.setOnClickListener {
            Toast.makeText(this, getString(R.string.notAvailable), Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickBtnCall() {
        binding.btnLlamar.setOnClickListener {
            Toast.makeText(this, getString(R.string.notAvailable), Toast.LENGTH_SHORT).show()
        }
    }
}