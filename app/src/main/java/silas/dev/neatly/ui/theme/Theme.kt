package silas.dev.neatly.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.toColorInt

private val DarkColorScheme = darkColorScheme(
    primary = Color("#3d348b".toColorInt()),
    secondary = Color("#7678ed".toColorInt()),
    tertiary = Color("#bcc7ed".toColorInt()),
    background = Color("#C0C3F7".toColorInt())
)

private val LightColorScheme = lightColorScheme(
    primary = Color("#3d348b".toColorInt()),
    secondary = Color("#7678ed".toColorInt()),
    tertiary = Color("#bcc7ed".toColorInt()),
    background = Color("#C0C3F7".toColorInt())
)

    // cd
//    --tekhelet: #3d348bff;
//--medium-slate-blue: #7678edff;
//--selective-yellow: #f7b801ff;
//--tangerine: #f18701ff;
//--persimmon: #f35b04ff;

    // 4
//    --space-cadet: #171738ff;
//--air-superiority-blue: #6899bbff;
//--periwinkle: #bcc7edff;
//--federal-blue: #2e1760ff;
//--lavender-floral: #a990dbff;
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

@Composable
fun NeatlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}