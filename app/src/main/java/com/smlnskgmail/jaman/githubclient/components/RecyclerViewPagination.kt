package com.smlnskgmail.jaman.githubclient.components

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewPagination(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    companion object {

        private var startPage = 1

    }

    override fun onScrolled(
        recyclerView: RecyclerView,
        dx: Int,
        dy: Int
    ) {
        val visibleItemsCount = layoutManager.childCount
        val totalItemsCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemsCount + firstVisibleItemPosition >= totalItemsCount
                && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

}
