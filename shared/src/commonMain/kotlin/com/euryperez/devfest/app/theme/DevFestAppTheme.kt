package com.euryperez.devfest.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DevFestAppTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme) {
        darkColors(
            primary = DarkPrimary,
            primaryVariant = DarkPrimaryVariant,
            secondary = DarkSecondary,
            background = Color(0xFF303030),
            surface = DarkSurface,
            onPrimary = DarkOnPrimary,
            onSecondary = DarkOnSecondary,
            onBackground = DarkOnBackground,
            onSurface = DarkOnSurface
        )
    } else {
        lightColors(
            primary = LightPrimary,
            primaryVariant = LightPrimaryVariant,
            secondary = LightSecondary,
            background = LightBackground,
            surface = LightSurface,
            onPrimary = LightOnPrimary,
            onSecondary = LightOnSecondary,
            onBackground = LightOnBackground,
            onSurface = LightOnSurface
        )
    }

    MaterialTheme(
        colors = colors,
        typography = DevFestTypography,
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(16.dp)
        ),
        content = content
    )
}