package com.cristian.proyectomercadolibre.framework.ui.items

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
import com.cristian.proyectomercadolibre.databinding.ActivityMainBinding
import com.cristian.proyectomercadolibre.framework.di.DaggerItemsComponents
import com.cristian.proyectomercadolibre.framework.di.ItemsModule
import com.cristian.proyectomercadolibre.framework.ui.adapters.items.ItemsActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.items.OnClickListenerCardView
import com.cristian.proyectomercadolibre.framework.ui.core.CustomLoadingDialog
import com.cristian.proyectomercadolibre.framework.ui.itemsDetails.ItemsDetailsActivity
import com.cristian.proyectomercadolibre.models.Result
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import javax.inject.Inject


class MainActivity : AppCompatActivity(), OnClickListenerCardView {
    private lateinit var itemsViewModel: ItemsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemsActivityAdapter
    private lateinit var dialogCustom: CustomLoadingDialog

    @Inject
    lateinit var itemViewModelFactory: ItemsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcRecycler.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        val search = intent.getStringExtra("KEY_CAT")

        DaggerItemsComponents.builder().itemsModule(ItemsModule(this)).build().inject(this)
        itemsViewModel = ViewModelProvider(this, itemViewModelFactory).get()
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
        binding.toolbarMaint.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if (binding.toolbarMaint.edtSearch.text?.isNotEmpty() == true) {
                        dialogCustom.showDialog()
                        startActivity(Intent(this, MainActivity::class.java).putExtra("KEY_CAT", binding.toolbarMaint.edtSearch.text.toString()))
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

    private fun observer() {
        itemsViewModel.items.observe(this, {
            when {
                it.results.isNotEmpty() -> {
                    dialogCustom.cancelDialog()
                    adapter = ItemsActivityAdapter(it.results, this)
                    binding.rcRecycler.adapter = adapter
                }
            }
        })

        itemsViewModel.errors.observe(this, {
            when (it) {
                is NetworkException -> {
                    binding.messageMain.root.visibility = View.VISIBLE
                    binding.messageMain.txtMessage.text = getString(R.string.internetConnection)
                }
                else -> {
                    binding.messageMain.root.visibility = View.VISIBLE
                    binding.messageMain.txtMessage.text = getString(R.string.genericError)
                }
            }
        })
    }

    override fun onClick(items: Result) {
        startActivity(
            Intent(this, ItemsDetailsActivity::class.java)
                .putExtra("KEY_RESULT", items)
        )
    }
}