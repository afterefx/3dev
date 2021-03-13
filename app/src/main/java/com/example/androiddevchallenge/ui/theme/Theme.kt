package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.seniorandroid.thirddevchallenge.util.LocalSysUiController

private val DarkColorPalette = darkColors(
    primary = yellow,
    onPrimary = gray900,
    background = gray900,
    surface = gray700,
    onBackground = Color.White,
    onSurface = Color.White,
)


private val LightColorPalette = lightColors(
    primary = yellow,
    onPrimary = gray900,
    background = purple,
    surface = Color.White,
    onBackground = Color.White,
    onSurface = gray900,
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val buttonElevation = 0.dp

@Composable
fun ThirdDevChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = LocalSysUiController.current
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}