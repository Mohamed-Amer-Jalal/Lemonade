package com.example.lemonade.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.ui.data.Lemonade
import kotlinx.coroutines.flow.MutableStateFlow

class LemonadeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LemonadeUiState())

    fun pickLemon() {
        _uiState.value = _uiState.value.copy(currentStep = 2, squeezeCount = (2..4).random())
    }

    fun squeezeLemon() {
        val squeezeCount = _uiState.value.squeezeCount - 1

        if (squeezeCount > 0) _uiState.value = _uiState.value.copy(squeezeCount = squeezeCount)
        else _uiState.value = _uiState.value.copy(currentStep = 3)
    }

    fun drinkLemonade() {
        _uiState.value = _uiState.value.copy(currentStep = 4)
    }

    fun restart() {
        _uiState.value = _uiState.value.copy(currentStep = 1, squeezeCount = 0)
    }

    @Composable
    fun OnLemonadeState() {
        // Collecting the current state from the StateFlow
        val lemonadeState = _uiState.collectAsState()

        val lemonade = when (lemonadeState.value.currentStep) {
            1 -> Lemonade(
                drawableResourceId = R.drawable.lemon_tree,
                textLabelResourceId = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = { pickLemon() }
            )

            2 -> Lemonade(
                drawableResourceId = R.drawable.lemon_squeeze,
                textLabelResourceId = R.string.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description,
                onImageClick = { squeezeLemon() }
            )

            3 -> Lemonade(
                drawableResourceId = R.drawable.lemon_drink,
                textLabelResourceId = R.string.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description,
                onImageClick = { drinkLemonade() }
            )

            4 -> Lemonade(
                drawableResourceId = R.drawable.lemon_restart,
                textLabelResourceId = R.string.lemon_empty_glass,
                contentDescriptionResourceId = R.string.empty_glass_content_description,
                onImageClick = { restart() }
            )

            else -> null // Handle unexpected states if needed
        }

        lemonade?.let { lemonade ->
            LemonTextAndImage(lemonade)
        }
    }
}