package com.example.lemonade.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.ui.data.Lemonade
import kotlinx.coroutines.flow.MutableStateFlow

class LemonadeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LemonadeUiState())

    private fun pickLemon() {
        _uiState.value = _uiState.value.copy(currentStep = 2, squeezeCount = (2..4).random())
    }

    private fun squeezeLemon() {
        val squeezeCount = _uiState.value.squeezeCount - 1

        if (squeezeCount > 0) _uiState.value = _uiState.value.copy(squeezeCount = squeezeCount)
        else _uiState.value = _uiState.value.copy(currentStep = 3)
    }

    private fun drinkLemonade() {
        _uiState.value = _uiState.value.copy(currentStep = 4)
    }

    private fun restart() {
        _uiState.value = _uiState.value.copy(currentStep = 1, squeezeCount = 0)
    }

    @Composable
    fun OnLemonadeState() {
        // Collecting the current state from the StateFlow
        val lemonadeState = _uiState.collectAsState()

        val lemonade = when (lemonadeState.value.currentStep) {
            1 -> Lemonade(
                R.drawable.lemon_tree,
                R.string.lemon_select,
                R.string.lemon_tree_content_description
            ) { pickLemon() }

            2 -> Lemonade(
                R.drawable.lemon_squeeze,
                R.string.lemon_squeeze,
                R.string.lemon_content_description
            ) { squeezeLemon() }

            3 -> Lemonade(
                R.drawable.lemon_drink,
                R.string.lemon_drink,
                R.string.lemonade_content_description
            ) { drinkLemonade() }

            4 -> Lemonade(
                R.drawable.lemon_restart,
                R.string.lemon_empty_glass,
                R.string.empty_glass_content_description
            ) { restart() }

            else -> null // Handle unexpected states if needed
        }

        lemonade?.let { lemonade ->
            LemonTextAndImage(lemonade)
        }
    }
}