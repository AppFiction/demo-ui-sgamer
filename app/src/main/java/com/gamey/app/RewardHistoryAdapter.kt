package com.gamey.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class RewardHistoryAdapter(
    private val context: Context, // List of image resources
    private val rewardImages: List<Int>
) :
    RecyclerView.Adapter<RewardHistoryAdapter.RewardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_reward, parent, false)
        return RewardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.img.setImageResource(rewardImages[position])
    }

    override fun getItemCount(): Int {
        return rewardImages.size
    }

    class RewardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.img)
    }
}
