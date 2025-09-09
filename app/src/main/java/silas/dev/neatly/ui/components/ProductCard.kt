package silas.dev.neatly.ui.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import silas.dev.neatly.domain.ProductInfo

@Composable
fun ProductCard(
    productInfo: ProductInfo,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    )
    {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Gray)
                .height(150.dp)
                .width(150.dp)
        ) {
            // TODO: Add image here
        }
        Column(
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .background(Color.White)
                .width(140.dp)
        ) {
            Text(
                text = productInfo.name,
                modifier = Modifier.padding(0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = productInfo.description,
                modifier = Modifier.padding(0.dp, 8.dp),
                fontSize = 12.sp
            )
        }
        DropdownMenu(onEditClick, onDeleteClick)
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    ProductCard(
        productInfo = ProductInfo(
            name = "item name",
            description = "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah",
            upcCode = "123456789"
        ),
        onEditClick = { },
    ) { }
}