package com.cristian.proyectomercadolibre

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cristian.proyectomercadolibre.databinding.ActivityMainBinding
import com.cristian.proyectomercadolibre.framework.di.DaggerItemsComponents
import com.cristian.proyectomercadolibre.framework.di.ItemsModule
import com.cristian.proyectomercadolibre.framework.ui.ItemsViewModel
import com.cristian.proyectomercadolibre.framework.ui.ItemsViewModelFactory
import com.cristian.proyectomercadolibre.framework.ui.adapters.ItemsActivityAdapter
import com.cristian.proyectomercadolibre.framework.ui.adapters.OnClickListenerCardView
import com.cristian.proyectomercadolibre.models.Result
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import javax.inject.Inject
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity(), OnClickListenerCardView {
    private lateinit var itemsViewModel: ItemsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemsActivityAdapter

    @Inject
    lateinit var itemViewModelFactory: ItemsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcRecycler.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)

        DaggerItemsComponents.builder().itemsModule(ItemsModule(this)).build().inject(this)
        itemsViewModel = ViewModelProvider(this, itemViewModelFactory).get()
        adapter = ItemsActivityAdapter(mutableListOf(), this)
        //searchItem()
        observer()

        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    itemsViewModel.getItems(binding.edtSearch.text.toString())
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
                    println("Tamaño data ${it.results.size}")
                    adapter = ItemsActivityAdapter(it.results, this)
                    binding.rcRecycler.adapter = adapter
                }
                else -> {
                    println("No data")
                }
            }
        })

        itemsViewModel.errors.observe(this, {
            when (it) {
                is NetworkException -> {
                    println("Error1 ${it.message}")
                }
                else -> {
                    println("Error2 ${it.message}")
                }
            }
        })
    }

    /*private fun searchItem() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                itemsViewModel.getItems(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }*/

    override fun onClick(items: Result) {
        println("Se hizo click")
    }
}