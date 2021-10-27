package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cristian.proyectomercadolibre.R
import com.cristian.proyectomercadolibre.databinding.ActivityCategoriesDetailsBinding
import com.cristian.proyectomercadolibre.framework.di.DaggerItemsComponents
import com.cristian.proyectomercadolibre.framework.di.ItemsModule
import com.cristian.proyectomercadolibre.framework.ui.adapters.categoriesDetails.CategoriesDetailsActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.categoriesDetails.OnClickListenerCategoriesDetailsCardView
import com.cristian.proyectomercadolibre.framework.ui.core.CustomLoadingDialog
import com.cristian.proyectomercadolibre.framework.ui.items.MainActivity
import com.cristian.proyectomercadolibre.framework.ui.itemsDetails.ItemsDetailsActivity
import com.cristian.proyectomercadolibre.models.Categories
import com.cristian.proyectomercadolibre.models.Result
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import javax.inject.Inject

class CategoriesDetailsActivity : AppCompatActivity(), OnClickListenerCategoriesDetailsCardView {
    private lateinit var categoriesDetailsViewModel: CategoriesDetailsViewModel
    private lateinit var binding: ActivityCategoriesDetailsBinding
    private lateinit var adapter: CategoriesDetailsActivityAdapter
    private lateinit var dialogCustom: CustomLoadingDialog
    @Inject
    lateinit var categoriesDetailsViewModelFactory: CategoriesDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcRecyclerCategoriesDetails.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        DaggerItemsComponents.builder().itemsModule(ItemsModule(this)).build().inject(this)
        categoriesDetailsViewModel = ViewModelProvider(this, categoriesDetailsViewModelFactory).get()
        dialogCustom = CustomLoadingDialog(this)
        getData()
        observer()
        textSearch()
    }

    private fun textSearch() {
        binding.toolbarCategoriesDetails.edtSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if (binding.toolbarCategoriesDetails.edtSearch.text?.isNotEmpty() == true) {
                        startActivity(Intent(this, MainActivity::class.java).putExtra("KEY_CAT", binding.toolbarCategoriesDetails.edtSearch.text.toString()))
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                        binding.toolbarCategoriesDetails.edtSearch.setText("")
                        binding.messageCategoriesDetails.root.visibility = View.GONE
                        onBackPressed()
                        true
                    }
                    false
                }
                else -> false
            }
        }
    }

    private fun getData() {
        dialogCustom.showDialog()
        intent.getParcelableExtra<Categories>("KEY_OBJ").let {
            if (it != null) {
                categoriesDetailsViewModel.getCategoriesDetails(it.id)
            }
        }
    }

    private fun observer() {
        categoriesDetailsViewModel.categories.observe(this, {
            when {
                it.results.isNotEmpty() -> {
                    dialogCustom.cancelDialog()
                    adapter = CategoriesDetailsActivityAdapter(it.results, this)
                    binding.rcRecyclerCategoriesDetails.adapter = adapter
                }
            }
        })

        categoriesDetailsViewModel.errors.observe(this, {
            dialogCustom.cancelDialog()
            when (it) {
                is NetworkException -> {
                    binding.messageCategoriesDetails.root.visibility = View.VISIBLE
                    binding.messageCategoriesDetails.txtMessage.text = getString(R.string.internetConnection)
                    textSearch()
                }
                else -> {
                    binding.messageCategoriesDetails.root.visibility = View.VISIBLE
                    binding.messageCategoriesDetails.txtMessage.text = getString(R.string.genericError)
                    textSearch()
                }
            }
        })
    }

    override fun onClick(details: Result) {
        startActivity(
            Intent(this, ItemsDetailsActivity::class.java)
                .putExtra("KEY_RESULT", details)
        )
    }
}