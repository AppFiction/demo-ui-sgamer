package com.gamey.app

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.gamey.app.databinding.ViewCustomBottomNavBinding

enum class TabType {
    HOME, REWARD, PROFILE
}

class CustomBottomBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ViewCustomBottomNavBinding =
        ViewCustomBottomNavBinding.inflate(LayoutInflater.from(context), this, true)

    var onTabSelected: ((TabType) -> Unit)? = null

    init {
        setupListeners()
        setActiveTab(TabType.HOME) // Default selection
    }

    private fun setupListeners() {
        binding.btnHome.setOnClickListener { setActiveTab(TabType.HOME) }
        binding.btnReward.setOnClickListener { setActiveTab(TabType.REWARD) }
        binding.btnProfile.setOnClickListener { setActiveTab(TabType.PROFILE) }
    }

    private fun setActiveTab(selectedTab: TabType) {
        resetTabColors()
        when (selectedTab) {
            TabType.HOME -> binding.btnHome.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_active))
            TabType.REWARD -> binding.btnReward.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_active))
            TabType.PROFILE -> binding.btnProfile.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_active))
        }
        onTabSelected?.invoke(selectedTab)
    }

    private fun resetTabColors() {
        binding.btnHome.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_inactive))
        binding.btnReward.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_inactive))
        binding.btnProfile.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_inactive))
    }
}