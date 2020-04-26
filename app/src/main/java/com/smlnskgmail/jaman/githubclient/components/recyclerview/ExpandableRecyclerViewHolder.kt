package com.smlnskgmail.jaman.githubclient.components.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ExpandableRecyclerViewHolder<T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}
