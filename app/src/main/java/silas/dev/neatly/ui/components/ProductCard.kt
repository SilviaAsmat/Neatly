package silas.dev.neatly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import silas.dev.neatly.ui.collections.CollectionProductItemViewState
import silas.dev.neatly.ui.components.photo_picker.DisplayProductPhoto
import silas.dev.neatly.ui.components.photo_picker.PhotoContent
import silas.dev.neatly.ui.products.ProductInfoViewState

@Composable
fun ProductCard(
    collectionProduct: CollectionProductItemViewState,
    onProductClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable { onProductClick(collectionProduct.productInfo.id) }
            .width(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        collectionProduct.photoUri?.let{
            PhotoContent(collectionProduct.photoUri)
        }

        Text(
            text = collectionProduct.productInfo.name,
            modifier = Modifier.padding(0.dp, 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = collectionProduct.productInfo.description,
            maxLines = 1,
            modifier = Modifier.padding(0.dp, 8.dp),
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    ProductCard(
        collectionProduct = CollectionProductItemViewState(
            productInfo = ProductInfoViewState(
            name = "item name",
            description = "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah",
            id = 0,
        ),
            photoUri = null),
        onProductClick = { Unit },
    )
}