package com.deloitte.series.coroutine.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.series.coroutine.core.Log

class PaginationRecyclerView : RecyclerView {

    private var previousVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    private var fetch = true

    var paginationListener: OnPaginationListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        val layoutManager = layoutManager
        if (layoutManager is LinearLayoutManager) {

            if (dy > 0) {
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                previousVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (fetch) {

                    if ((visibleItemCount + previousVisibleItems) >= totalItemCount) {
                        fetch = false
                        paginationListener?.onNextPageLoad()
                        fetch = true
                    }
                }

            }

        } else {
            Log.error("Use a LinearLayoutManager")
        }
    }

    interface OnPaginationListener {

        fun onNextPageLoad()
    }
}