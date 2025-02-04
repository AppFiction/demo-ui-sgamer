package com.gamey.app

import HelpItem
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help) // Replace with your actual layout containing RecyclerView

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        val rewards = listOf(
            HelpItem(1, "How to earn coin?"),
            HelpItem(2, "How to ?"),
            HelpItem(3, "Can I use Vpn?"),
            HelpItem(4, "Why My Coin Not credited?"),
            HelpItem(5, "How to complain issue?"),
            HelpItem(6, "Why  a transaction rejected?"),
            HelpItem(7, "Why  a transaction pending?"),
            HelpItem(8, "Why  a transaction refunded?")
        )

        recyclerView.adapter = HelpAdapter(rewards)

    }
}
