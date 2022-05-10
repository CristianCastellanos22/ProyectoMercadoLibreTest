package com.cristian.proyectomercadolibre.framework.ui.categories

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityCategoriesBinding
import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.framework.delegate.viewBinding
import com.cristian.proyectomercadolibre.framework.ui.adapters.categories.CategoriesActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.categories.OnClickListenerCategoriesCardView
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsActivity
import com.cristian.proyectomercadolibre.framework.ui.items.MainActivity
import com.cristian.proyectomercadolibre.utils.KEY_CAT
import com.cristian.proyectomercadolibre.utils.KEY_OBJ
import java.net.UnknownHostException

class CategoriesActivity : AppCompatActivity(), OnClickListenerCategoriesCardView {
    private val categoriesViewModel: CategoriesViewModel by viewModels(
        factoryProducer = {
            CategoriesViewModelFactory()
        }
    )
    private val binding: ActivityCategoriesBinding by viewBinding()
    private lateinit var adapter: CategoriesActivityAdapter
    private var data: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CategoriesActivityAdapter(mutableListOf(), this)
        binding.rcRecyclerCategories.adapter = adapter
        categoriesViewModel.getCategories()
        observer()
        textSearch()

    }

    private fun textSearch() {
        with(binding) {
            toolbarCategories.edtSearch.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        if (toolbarCategories.edtSearch.text?.isNotEmpty() == true) {
                            if (data) {
                                startActivity(
                                    Intent(
                                        this@CategoriesActivity,
                                        MainActivity::class.java
                                    ).putExtra(
                                        KEY_CAT,
                                        toolbarCategories.edtSearch.text.toString()
                                    )
                                )
                            }
                            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                            toolbarCategories.edtSearch.setText("")
                            true
                        }
                        false
                    }
                    else -> false
                }
            }
        }
    }

    private fun observer() {
        with(binding) {
            categoriesViewModel.categories.observe(this@CategoriesActivity) {
                when {
                    it.isNotEmpty() -> {
                        data = true
                        messageViewCategories.root.visibility = View.GONE
                        adapter = CategoriesActivityAdapter(it, this@CategoriesActivity)
                        rcRecyclerCategories.adapter = adapter
                    }
                }
            }

            categoriesViewModel.errors.observe(this@CategoriesActivity) {
                txtCat.visibility = View.GONE
                when (it) {
                    is UnknownHostException -> {
                        data = false
                        messageViewCategories.root.visibility = View.VISIBLE
                        messageViewCategories.txtMessage.text =
                            getString(R.string.internetConnection)
                    }
                    else -> {
                        messageViewCategories.root.visibility = View.VISIBLE
                        messageViewCategories.txtMessage.text = getString(R.string.genericError)
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    categoriesViewModel.getCategories()
                }, 5000)
            }
        }
    }

    override fun onClick(categories: Categories) {
        startActivity(
            Intent(this, CategoriesDetailsActivity::class.java).putExtra(KEY_OBJ, categories)
        )
    }
}
