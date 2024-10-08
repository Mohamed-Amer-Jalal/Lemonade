package com.example.lemonade.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.lemonade.LemonTextAndImage
import com.example.lemonade.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LemonadeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LemonadeUiState())
    val uiState: StateFlow<LemonadeUiState> = _uiState.asStateFlow()

    @Composable
    fun OnImageClicked() {
        when (_uiState.value.currentStep) {
            1 -> LemonTextAndImage(
                onImageClick = {
                    _uiState.value.currentStep = 2.also { _uiState.value.squeezeCount = (2..4).random() }
                },
                drawableResourceId = R.drawable.lemon_tree,
                textLabelResourceId = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description
            )

            2 -> LemonTextAndImage(
                onImageClick = { if (_uiState.value.squeezeCount-- == 0) _uiState.value.currentStep = 3 },
                drawableResourceId = R.drawable.lemon_squeeze,
                textLabelResourceId = R.string.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description
            )

            3 -> LemonTextAndImage(
                onImageClick = { _uiState.value.currentStep = 4 },
                drawableResourceId = R.drawable.lemon_drink,
                textLabelResourceId = R.string.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description
            )

            4 -> LemonTextAndImage(
                onImageClick = { _uiState.value.currentStep = 1 },
                drawableResourceId = R.drawable.lemon_restart,
                textLabelResourceId = R.string.lemon_empty_glass,
                contentDescriptionResourceId = R.string.empty_glass_content_description
            )
        }
    }
}