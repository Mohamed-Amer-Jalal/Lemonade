package com.example.lemonade.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.ui.data.Lemonade

class LemonadeViewModel : ViewModel() {
    private val _currentStep = mutableIntStateOf(1)

    private val _squeezeCount = mutableIntStateOf(0)

    // Function to get the current Lemonade state
    @Composable
    fun LemonadeState() {

        val lemonade = when (_currentStep.intValue) {
            1 -> Lemonade(
                drawableResourceId = R.drawable.lemon_tree,
                textLabelResourceId = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = { _currentStep.intValue = 2.also { _squeezeCount.intValue = (2..4).random() } }
            )

            2 -> Lemonade(
                drawableResourceId = R.drawable.lemon_squeeze,
                textLabelResourceId = R.string.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description,
                onImageClick = { if (_squeezeCount.intValue-- <= 0) _currentStep.intValue = 3 }
            )

            3 -> Lemonade(
                drawableResourceId = R.drawable.lemon_drink,
                textLabelResourceId = R.string.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description,
                onImageClick = { _currentStep.intValue = 4 }
            )

            4 -> Lemonade(
                drawableResourceId = R.drawable.lemon_restart,
                textLabelResourceId = R.string.lemon_empty_glass,
                contentDescriptionResourceId = R.string.empty_glass_content_description,
                onImageClick = { _currentStep.intValue = 1 }
            )

            else -> Lemonade(
                drawableResourceId = R.drawable.lemon_tree,
                textLabelResourceId = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = { }
            )
        }

        LemonTextAndImage(lemonade)
    }
}