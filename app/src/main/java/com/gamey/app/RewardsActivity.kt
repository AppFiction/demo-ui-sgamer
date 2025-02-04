package com.gamey.app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RewardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewards) // Replace with your actual layout containing RecyclerView

        val backBtn: ImageView = findViewById(R.id.backBtn)
        val rejectedRv: RecyclerView = findViewById(R.id.rejectedRv)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        rejectedRv.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        backBtn.setOnClickListener {
            finish()
        }

        val rejectData = listOf(
            R.drawable.item_reward_red,
            R.drawable.item_reward_red
        )

        val data = listOf(
            R.drawable.item_reward_green,
            R.drawable.item_reward_yellow
        )

        val adapterReject = RewardHistoryAdapter(this, rejectData)
        val adapter = RewardHistoryAdapter(this, data)
        rejectedRv.adapter = adapterReject
        recyclerView.adapter = adapter
    }
}
