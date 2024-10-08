package com.example.lemonade.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.R
import com.example.lemonade.ui.theme.LemonadeTheme

@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        LemonadeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Lemonade", fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
    )
}

@Composable
fun LemonadeScreen(
    modifier: Modifier = Modifier,
    viewModel: LemonadeViewModel = LemonadeViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        viewModel.OnImageClicked()
    }
}

@Composable
fun LemonTextAndImage(
    onImageClick: () -> Unit,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    textLabelResourceId: Int
) {
    Button(
        onClick = onImageClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .width(dimensionResource(R.dimen.button_image_width))
                .height(dimensionResource(R.dimen.button_image_height))
                .padding(dimensionResource(R.dimen.button_interior_padding))
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
    Text(
        text = stringResource(textLabelResourceId),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonPreview() {
    LemonadeTheme(darkTheme = false) {
        LemonadeApp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonPreviewDark() {
    LemonadeTheme(darkTheme = true) {
        LemonadeApp()
    }
}