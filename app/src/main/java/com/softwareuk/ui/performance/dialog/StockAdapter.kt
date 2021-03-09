package com.softwareuk.ui.performance.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.LineDataSet
import com.softwareuk.R
import kotlinx.android.synthetic.main.item_stock.view.*

class StockAdapter(
    private val onActionClickListener: (LineDataSet) -> Unit
) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    private val items = mutableListOf<LineDataSet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, onActionClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<LineDataSet>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(
        parent: ViewGroup,
        private val onActionClickListener: (LineDataSet) -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock, parent, false)
    ) {
        private val textView = itemView.textView

        fun bind(value: LineDataSet) {
            textView.text = value.label
            itemView.setOnClickListener {
                onActionClickListener.invoke(value)
            }
        }
    }
}