package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

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
import com.cristian.proyectomercadolibre.databinding.ActivityCategoriesDetailsBinding
import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.domain.models.Product
import com.cristian.proyectomercadolibre.framework.delegate.viewBinding
import com.cristian.proyectomercadolibre.framework.ui.adapters.categoriesDetails.CategoriesDetailsActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.categoriesDetails.OnClickListenerCategoriesDetailsCardView
import com.cristian.proyectomercadolibre.framework.ui.core.CustomLoadingDialog
import com.cristian.proyectomercadolibre.framework.ui.items.MainActivity
import com.cristian.proyectomercadolibre.framework.ui.itemsDetails.ItemsDetailsActivity
import com.cristian.proyectomercadolibre.utils.KEY_CAT
import com.cristian.proyectomercadolibre.utils.KEY_OBJ
import com.cristian.proyectomercadolibre.utils.KEY_RESULT
import java.net.UnknownHostException

class CategoriesDetailsActivity : AppCompatActivity(), OnClickListenerCategoriesDetailsCardView {
    private val categoriesDetailsViewModel: CategoriesDetailsViewModel by viewModels(
        factoryProducer = {
            CategoriesDetailsViewModelFactory()
        }
    )
    private val binding: ActivityCategoriesDetailsBinding by viewBinding()
    private lateinit var adapter: CategoriesDetailsActivityAdapter
    private lateinit var dialogCustom: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rcRecyclerCategoriesDetails.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        dialogCustom = CustomLoadingDialog(this)
        getData()
        observer()
        textSearch()
    }

    private fun textSearch() {
        with(binding) {
            toolbarCategoriesDetails.edtSearch.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        if (toolbarCategoriesDetails.edtSearch.text?.isNotEmpty() == true) {
                            startActivity(
                                Intent(
                                    this@CategoriesDetailsActivity,
                                    MainActivity::class.java
                                ).putExtra(
                                    KEY_CAT,
                                    toolbarCategoriesDetails.edtSearch.text.toString()
                                )
                            )
                            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                            toolbarCategoriesDetails.edtSearch.setText("")
                            messageCategoriesDetails.root.visibility = View.GONE
                            onBackPressed()
                            true
                        }
                        false
                    }
                    else -> false
                }
            }
        }
    }

    private fun getData() {
        dialogCustom.showDialog()
        intent.getParcelableExtra<Categories>(KEY_OBJ).let {
            if (it != null) {
                categoriesDetailsViewModel.getCategoriesDetails(it.id)
            }
        }
    }

    private fun observer() {
        with(binding) {
            categoriesDetailsViewModel.categories.observe(this@CategoriesDetailsActivity) {
                when {
                    it.products.isNotEmpty() -> {
                        dialogCustom.cancelDialog()
                        adapter = CategoriesDetailsActivityAdapter(
                            it.products,
                            this@CategoriesDetailsActivity
                        )
                        rcRecyclerCategoriesDetails.adapter = adapter
                    }
                }
            }

            categoriesDetailsViewModel.errors.observe(this@CategoriesDetailsActivity) {
                dialogCustom.cancelDialog()
                when (it) {
                    is UnknownHostException -> {
                        messageCategoriesDetails.root.visibility = View.VISIBLE
                        messageCategoriesDetails.txtMessage.text =
                            getString(R.string.internetConnection)
                        textSearch()
                    }
                    else -> {
                        messageCategoriesDetails.root.visibility = View.VISIBLE
                        messageCategoriesDetails.txtMessage.text =
                            getString(R.string.genericError)
                        textSearch()
                    }
                }
            }
        }
    }

    override fun onClick(details: Product) {
        startActivity(
            Intent(this, ItemsDetailsActivity::class.java)
                .putExtra(KEY_RESULT, details)
        )
    }
}
