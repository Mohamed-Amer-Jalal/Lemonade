package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Lemonade", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        LemonadeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
fun LemonadeScreen(
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    onImageClick = { currentStep = 2.also { squeezeCount = (2..4).random() } },
                    drawableResourceId = R.drawable.lemon_tree,
                    textLabelResourceId = R.string.lemon_select,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                )
            }

            2 -> {
                LemonTextAndImage(
                    onImageClick = { if (squeezeCount-- == 0) currentStep = 3 },
                    drawableResourceId = R.drawable.lemon_squeeze,
                    textLabelResourceId = R.string.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                )
            }

            3 -> {
                LemonTextAndImage(
                    onImageClick = { currentStep = 4 },
                    drawableResourceId = R.drawable.lemon_drink,
                    textLabelResourceId = R.string.lemon_drink,
                    contentDescriptionResourceId = R.string.lemonade_content_description,
                )
            }

            4 -> {
                LemonTextAndImage(
                    onImageClick = { currentStep = 1 },
                    drawableResourceId = R.drawable.lemon_restart,
                    textLabelResourceId = R.string.lemon_empty_glass,
                    contentDescriptionResourceId = R.string.empty_glass_content_description
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    onImageClick: () -> Unit,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    textLabelResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = Modifier
                    .width(128.dp)
                    .height(160.dp)
                    .padding(24.dp)
            )
        }
        Spacer(modifier = modifier.height(32.dp))
        Text(
            text = stringResource(textLabelResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonPreview() {
    LemonadeApp()
}