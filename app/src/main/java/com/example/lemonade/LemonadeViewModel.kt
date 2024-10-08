package com.example.lemonade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class LemonadeViewModel : ViewModel() {
    private val _currentStep = mutableIntStateOf(1)

    private val _squeezeCount = mutableIntStateOf(0)

    @Composable
    fun OnImageClicked() {
        when (_currentStep.intValue) {
            1 -> LemonTextAndImage(
                onImageClick = {
                    _currentStep.intValue = 2.also { _squeezeCount.intValue = (2..4).random() }
                },
                drawableResourceId = R.drawable.lemon_tree,
                textLabelResourceId = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description
            )

            2 -> LemonTextAndImage(
                onImageClick = { if (_squeezeCount.intValue-- == 0) _currentStep.intValue = 3 },
                drawableResourceId = R.drawable.lemon_squeeze,
                textLabelResourceId = R.string.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description
            )

            3 -> LemonTextAndImage(
                onImageClick = { _currentStep.intValue = 4 },
                drawableResourceId = R.drawable.lemon_drink,
                textLabelResourceId = R.string.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description
            )

            4 -> LemonTextAndImage(
                onImageClick = { _currentStep.intValue = 1 },
                drawableResourceId = R.drawable.lemon_restart,
                textLabelResourceId = R.string.lemon_empty_glass,
                contentDescriptionResourceId = R.string.empty_glass_content_description
            )
        }
    }
}