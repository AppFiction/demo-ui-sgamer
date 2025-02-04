package com.gamey.app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoinsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins) // Replace with your actual layout containing RecyclerView

        val backBtn: ImageView = findViewById(R.id.backBtn)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        backBtn.setOnClickListener {
            finish()
        }

        val data = listOf(
            R.drawable.coin_history1,
            R.drawable.coin_history2
        )

        val adapter = RewardHistoryAdapter(this, data)
        recyclerView.adapter = adapter
    }
}
