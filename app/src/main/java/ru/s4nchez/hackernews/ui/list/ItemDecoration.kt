package ru.s4nchez.hackernews.ui.list

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecoration(val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (view == null || outRect == null || parent == null) return

        val position = parent.getChildAdapterPosition(view)
        if (position + 1 == state?.itemCount) outRect.bottom = spacing
        outRect.top = spacing
    }
}