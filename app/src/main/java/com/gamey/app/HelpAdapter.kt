package com.gamey.app

import HelpItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HelpAdapter(private val items: List<HelpItem>) :
    RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {

    inner class HelpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countText: TextView = itemView.findViewById(R.id.count)
        private val titleText: TextView = itemView.findViewById(R.id.title)
        private val expandableView: View = itemView.findViewById(R.id.expandableView)
        private val showButton: ImageView = itemView.findViewById(R.id.show)
        private val closeButton: ImageView = itemView.findViewById(R.id.close)
        private val mainLayout: LinearLayout = itemView.findViewById(R.id.main)

        fun bind(item: HelpItem) {
            countText.text = String.format("%02d", item.count)
            titleText.text = item.title

            // Toggle visibility based on isExpanded state
            expandableView.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            showButton.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            closeButton.visibility = if (item.isExpanded) View.GONE else View.VISIBLE

            mainLayout.setOnClickListener {
                item.isExpanded = !item.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_faq, parent, false)
        return HelpViewHolder(view)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
