package com.example.lemonade.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Lemonade(
    @DrawableRes val drawableResourceId: Int,
    @StringRes val textLabelResourceId: Int,
    @StringRes val contentDescriptionResourceId: Int,
    val onImageClick: () -> Unit
)

data class LemonadeState(
    val currentStep: Int = 1,
    val squeezeCount: Int = 0
)