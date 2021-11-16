package com.deloitte.series.coroutine

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.deloitte.series.R
import com.google.android.material.snackbar.Snackbar

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flickr_gallery_activity)
        //TODO
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