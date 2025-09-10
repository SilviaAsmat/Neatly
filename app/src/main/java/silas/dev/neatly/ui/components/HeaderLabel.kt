package silas.dev.neatly.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderLabel(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .padding(top = 0.dp, bottom = 6.dp, start = 16.dp),
        color = Color.Black,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    )
}