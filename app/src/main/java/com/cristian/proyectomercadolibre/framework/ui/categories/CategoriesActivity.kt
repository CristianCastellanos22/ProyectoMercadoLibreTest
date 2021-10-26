package com.cristian.proyectomercadolibre.framework.ui.categories

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityCategoriesBinding
import com.cristian.proyectomercadolibre.framework.di.DaggerItemsComponents
import com.cristian.proyectomercadolibre.framework.di.ItemsModule
import com.cristian.proyectomercadolibre.framework.ui.adapters.categories.CategoriesActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.categories.OnClickListenerCategoriesCardView
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsActivity
import com.cristian.proyectomercadolibre.framework.ui.items.MainActivity
import com.cristian.proyectomercadolibre.models.Categories
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import javax.inject.Inject


class CategoriesActivity : AppCompatActivity(), OnClickListenerCategoriesCardView {
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var adapter: CategoriesActivityAdapter
    @Inject
    lateinit var categoriesViewModelFactory: CategoriesViewModelFactory
    private var data: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CategoriesActivityAdapter(mutableListOf(),this)
        binding.rcRecyclerCategories.adapter = adapter

        DaggerItemsComponents.builder().itemsModule(ItemsModule(this)).build().inject(this)
        categoriesViewModel = ViewModelProvider(this, categoriesViewModelFactory).get()

        categoriesViewModel.getCategories()
        observer()
        textSearch()

    }

    private fun textSearch() {
        binding.toolbarCategories.edtSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if (binding.toolbarCategories.edtSearch.text?.isNotEmpty() == true) {
                        println("Se buscara: ${binding.toolbarCategories.edtSearch.text}")
                        if (data) {
                            startActivity(Intent(this, MainActivity::class.java).putExtra("KEY_CAT", binding.toolbarCategories.edtSearch.text.toString()))
                        }
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        binding.toolbarCategories.edtSearch.setText("")
                        true
                    }
                    false
                }
                else -> false
            }
        }
    }

    private fun observer() {
        categoriesViewModel.categories.observe(this, {
            when {
                it.isNotEmpty() -> {
                    data = true
                    binding.messageViewCategories.root.visibility = View.GONE
                    adapter = CategoriesActivityAdapter(it,this)
                    binding.rcRecyclerCategories.adapter = adapter
                }
            }
        })

        categoriesViewModel.errors.observe(this, {
            binding.txtCat.visibility = View.GONE
            when (it) {
                is NetworkException -> {
                    data = false
                    binding.messageViewCategories.root.visibility = View.VISIBLE
                    binding.messageViewCategories.txtMessage.text = getString(R.string.internetConnection)
                }
                else -> {
                    binding.messageViewCategories.root.visibility = View.VISIBLE
                    binding.messageViewCategories.txtMessage.text = getString(R.string.genericError)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                categoriesViewModel.getCategories()
            }, 5000)
        })
    }

    override fun onClick(categories: Categories) {
        println("Objeto de categoria: ${categories.name}")
        startActivity(
            Intent(this, CategoriesDetailsActivity::class.java).putExtra("KEY_OBJ", categories)
        )
    }
}