package com.example.quicknote.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.R


abstract class SwipeToDeleteNote(
    context: Context
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {

    private val iconDelete = ContextCompat.getDrawable(
        context,
        R.drawable.ic_delete
    ) ?: error("iconDelete is null")
    private val iconWidth = iconDelete.intrinsicWidth
    private val iconHeight = iconDelete.intrinsicHeight
    private val backgroundColor = ResourcesCompat.getColor(
        context.resources,
        R.color.md_theme_dark_error,
        null
    )
    private val clearPaint = Paint().apply {
        alpha = 0
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val isCanceled = dX == 0F || !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                c,
                itemView.left.toFloat(),
                itemView.top.toFloat(),
                (itemView.left + dX.toInt()).toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        val radius = itemView.context.resources.getDimensionPixelSize(
            R.dimen.note_card_corner_radius
        ).toFloat()
        val corners = floatArrayOf(
            radius, radius,//top left corner
            0F, 0F,
            0F, 0F,
            radius, radius //bottom left corner
        )

        val path = Path().apply {
            addRoundRect(
                itemView.left.toFloat(),
                itemView.top.toFloat(),
                (itemView.left + dX.toInt()).toFloat(),
                itemView.bottom.toFloat(),
                corners,
                Path.Direction.CW
            )
        }

        c.drawPath(path, Paint().apply { color = backgroundColor })

        val iconDeleteMargin = itemView.context.resources.getDimensionPixelSize(
            R.dimen.delete_icon_margin
        )
        val iconDeleteTop = itemView.bottom - (itemView.height) / 2 - iconHeight / 2
        val iconDeleteLeft = itemView.left + iconDeleteMargin
        val iconDeleteRight = itemView.left + iconDeleteMargin + iconWidth
        val iconDeleteBottom = iconDeleteTop + iconHeight

        iconDelete.setBounds(
            iconDeleteLeft,
            iconDeleteTop,
            iconDeleteRight,
            iconDeleteBottom
        )
        iconDelete.draw(c)

        val newdX = if (dX >= itemView.width / 2) (itemView.width / 2).toFloat() else dX
        super.onChildDraw(c, recyclerView, viewHolder, newdX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }

}