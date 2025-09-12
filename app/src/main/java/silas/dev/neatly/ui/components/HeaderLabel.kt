package silas.dev.neatly.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderLabel(name: String, onCollectionClick: () -> Unit) {
    Text(
        text = name,// TODO validate collection name in user input and db
        modifier = Modifier
            .padding(top = 0.dp, bottom = 6.dp, start = 16.dp)
            .clickable {onCollectionClick()},
        color = Color.Black,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    )
}