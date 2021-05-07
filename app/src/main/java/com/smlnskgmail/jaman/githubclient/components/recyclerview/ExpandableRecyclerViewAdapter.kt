package com.smlnskgmail.jaman.githubclient.components.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class ExpandableRecyclerViewAdapter<T>
    : RecyclerView.Adapter<ExpandableRecyclerViewHolder<T>>() {

    companion object {

        const val viewTypeItem = 0
        const val viewTypeLoading = 1

    }

    private var loaderIsVisible = false

    fun addMore(items: List<T>) {
        val lastIndex = itemCount
        items().addAll(items)
        notifyItemRangeInserted(
            lastIndex,
            items.size
        )
    }

    fun loadingStarted() {
        if (itemCount != 0) {
            loaderIsVisible = true
            items().add(loaderItem())
            notifyItemInserted(itemCount - 1)
        }
    }

    abstract fun items(): MutableList<T>

    abstract fun loaderItem(): T

    fun loadingEnded() {
        loaderIsVisible = false
        val progressItemIndex = itemCount - 1
        notifyItemRemoved(progressItemIndex)
        items().removeAt(progressItemIndex)
    }

    override fun onBindViewHolder(
        holder: ExpandableRecyclerViewHolder<T>,
        position: Int
    ) {
        holder.bind(items()[position])
    }

    override fun getItemViewType(
        position: Int
    ): Int {
        return if (loaderIsVisible) {
            if (position == itemCount - 1) {
                viewTypeLoading
            } else {
                viewTypeItem
            }
        } else {
            viewTypeItem
        }
    }

    override fun getItemCount() = items().size

}
