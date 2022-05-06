package com.cristian.proyectomercadolibre.framework.ui.items

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityMainBinding
import com.cristian.proyectomercadolibre.domain.models.Product
import com.cristian.proyectomercadolibre.framework.delegate.viewBinding
import com.cristian.proyectomercadolibre.framework.ui.adapters.items.ItemsActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.items.OnClickListenerCardView
import com.cristian.proyectomercadolibre.framework.ui.core.CustomLoadingDialog
import com.cristian.proyectomercadolibre.framework.ui.itemsDetails.ItemsDetailsActivity
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import com.cristian.proyectomercadolibre.utils.KEY_CAT
import com.cristian.proyectomercadolibre.utils.KEY_RESULT


class MainActivity : AppCompatActivity(), OnClickListenerCardView {
    private val itemsViewModel: ItemsViewModel by viewModels(
        factoryProducer = {
            ItemsViewModelFactory()
        }
    )
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var adapter: ItemsActivityAdapter
    private lateinit var dialogCustom: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rcRecycler.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        val search = intent.getStringExtra(KEY_CAT)
        dialogCustom = CustomLoadingDialog(this)
        dialogCustom.showDialog()
        adapter = ItemsActivityAdapter(mutableListOf(), this)
        observer()
        searchTextButton()

        if (search != null) {
            searchData(search)
        }
    }

    private fun searchData(data: String) {
        if (data.isNotEmpty()) {
            itemsViewModel.getItems(data)
        }
    }

    private fun searchTextButton() {
        with(binding) {
            toolbarMaint.edtSearch.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        if (toolbarMaint.edtSearch.text?.isNotEmpty() == true) {
                            dialogCustom.showDialog()
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    MainActivity::class.java
                                ).putExtra(KEY_CAT, toolbarMaint.edtSearch.text.toString())
                            )
                            onBackPressed()
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun observer() {
        with(binding) {
            itemsViewModel.items.observe(this@MainActivity) {
                when {
                    it.products.isNotEmpty() -> {
                        dialogCustom.cancelDialog()
                        adapter = ItemsActivityAdapter(it.products, this@MainActivity)
                        rcRecycler.adapter = adapter
                    }
                }
            }

            itemsViewModel.errors.observe(this@MainActivity) {
                when (it) {
                    is NetworkException -> {
                        messageMain.root.visibility = View.VISIBLE
                        messageMain.txtMessage.text = getString(R.string.internetConnection)
                    }
                    else -> {
                        messageMain.root.visibility = View.VISIBLE
                        messageMain.txtMessage.text = getString(R.string.genericError)
                    }
                }
            }
        }
    }

    override fun onClick(items: Product) {
        startActivity(
            Intent(this, ItemsDetailsActivity::class.java)
                .putExtra(KEY_RESULT, items)
        )
    }
}
