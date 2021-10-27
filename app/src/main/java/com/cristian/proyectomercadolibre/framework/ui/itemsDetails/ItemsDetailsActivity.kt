package com.cristian.proyectomercadolibre.framework.ui.itemsDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityItemsDetailsBinding
import com.cristian.proyectomercadolibre.models.Result
import com.cristian.proyectomercadolibre.utils.FormatNumber

class ItemsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarItemsDetails.edtSearch.visibility = View.GONE
        getData()
        clickBtnQuestion()
        clickBtnCall()
    }

    private fun getData() {
        intent.getParcelableExtra<Result>("KEY_RESULT").let {
            if (it != null) {
                binding.txtNameDetails.text = it.title
                Glide.with(applicationContext).load(it.thumbnail)
                    .error(R.drawable.ic_baseline_cloud_off_24)
                    .fitCenter()
                    .into(binding.imgProfileDetails)
                binding.txtPriceDetails.text = FormatNumber.formatNumber(it.price.toDouble())
                binding.addressSeller.text =
                    it.seller_address.city.name + " " + it.seller_address.state.name
            }


        }
    }

    private fun clickBtnQuestion() {
        binding.btnQuestion.setOnClickListener {
            Toast.makeText(this, "No disponible de momento.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickBtnCall() {
        binding.btnLlamar.setOnClickListener {
            Toast.makeText(this, "No disponible de momento.", Toast.LENGTH_SHORT).show()
        }
    }
}