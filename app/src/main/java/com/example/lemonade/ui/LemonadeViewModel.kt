package com.example.lemonade.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.ui.data.Lemonade
import kotlinx.coroutines.flow.MutableStateFlow

class LemonadeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LemonadeUiState())

    @Composable
    fun OnLemonadeState() {
        // Collecting the current state from the StateFlow
        val lemonadeState = _uiState.collectAsState()

        val lemonade = when (lemonadeState.value.currentStep) {
            1 -> Lemonade(
                R.drawable.lemon_tree,
                R.string.lemon_select,
            ) {
                _uiState.value =
                    _uiState.value.copy(currentStep = 2, squeezeCount = (2..4).random())
            }

            2 -> Lemonade(
                R.drawable.lemon_squeeze,
                R.string.lemon_squeeze,
            ) {
                val squeezeCount = _uiState.value.squeezeCount - 1

                if (squeezeCount > 0) _uiState.value =
                    _uiState.value.copy(squeezeCount = squeezeCount)
                else _uiState.value = _uiState.value.copy(currentStep = 3)
            }

            3 -> Lemonade(
                R.drawable.lemon_drink,
                R.string.lemon_drink,
            ) { _uiState.value = _uiState.value.copy(currentStep = 4) }

            else -> Lemonade(
                R.drawable.lemon_restart,
                R.string.lemon_empty_glass,
            ) { _uiState.value = _uiState.value.copy(currentStep = 1, squeezeCount = 0) }
        }

        LemonTextAndImage(lemonade)
    }
}