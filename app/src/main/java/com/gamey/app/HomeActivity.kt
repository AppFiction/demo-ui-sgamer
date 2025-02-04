package com.gamey.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gamey.app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.onTabSelected = { selectedTab ->
            updateTabVisibility(selectedTab)
        }

        binding.contentTabReward.rewardHistory.setOnClickListener {
            openActivity(RewardsActivity::class.java)

        }

        binding.contentTabProfile.rewardHistory.setOnClickListener {
            openActivity(RewardsActivity::class.java)
        }

        binding.contentTabProfile.accountHistory.setOnClickListener {
            openActivity(CoinsActivity::class.java)
        }

        binding.contentTabProfile.helpFaq.setOnClickListener {
            openActivity(HelpActivity::class.java)
        }
    }

    private fun updateTabVisibility(selectedTab: TabType) {
        binding.contentTabHome.main.visibility =
            if (selectedTab == TabType.HOME) View.VISIBLE else View.GONE
        binding.contentTabReward.main.visibility =
            if (selectedTab == TabType.REWARD) View.VISIBLE else View.GONE
        binding.contentTabProfile.main.visibility =
            if (selectedTab == TabType.PROFILE) View.VISIBLE else View.GONE
    }
}

fun Context.openActivity(activityClass: Class<*>) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
}