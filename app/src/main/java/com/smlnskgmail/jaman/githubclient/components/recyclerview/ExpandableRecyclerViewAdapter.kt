package com.smlnskgmail.jaman.githubclient.components.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class ExpandableRecyclerViewAdapter<T>
    : RecyclerView.Adapter<ExpandableRecyclerViewHolder<T>>() {

    abstract fun addMore(
        items: List<T>
    )

}
