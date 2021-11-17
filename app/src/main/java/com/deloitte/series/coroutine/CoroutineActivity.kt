package com.deloitte.series.coroutine

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.deloitte.series.R
import com.deloitte.series.coroutine.network.exception.GenericException
import com.deloitte.series.coroutine.network.exception.NetworkException
import com.deloitte.series.coroutine.network.exception.RestException
import com.deloitte.series.coroutine.widget.PaginationRecyclerView
import com.google.android.material.snackbar.Snackbar

class CoroutineActivity : AppCompatActivity() {

    private lateinit var viewModel: CoroutineViewModel
    private val photosAdapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flickr_gallery_activity)
        viewModel = ViewModelProvider(this).get(CoroutineViewModel::class.java)

        val recyclerView = findViewById<PaginationRecyclerView>(R.id.recycler_view)
        recyclerView.adapter = photosAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        registerObservers()
        viewModel.fetchPhotos()
    }

    fun registerObservers() {
        viewModel.success.observe(this) {

            photosAdapter.apply {
                photos.addAll(it)
                notifyDataSetChanged()
            }
        }

        viewModel.error.observe(this) { error ->

            when (error) {
                is NetworkException -> showSnackbar(R.string.network_error_message)
                is RestException -> error.msg?.let { showSnackbar(it) }
                else -> showSnackbar(R.string.generic_error_message)
            }
        }
    }

    private fun showSnackbar(@StringRes message: Int) {
        Snackbar.make(
            findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showSnackbar(message: String) {
        Snackbar.make(
            findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}