package silas.dev.neatly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import silas.dev.neatly.ui.products.ProductInfoViewState

@Composable
fun RowOfProducts(
    products: List<ProductInfoViewState>,
    onProductClick: (Int) -> Unit,
){
    LazyRow(
        modifier = Modifier
            .heightIn(max = 260.dp)
            .padding(start = 12.dp, end = 0.dp, bottom = 0.dp, top = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(count = products.size) { index ->
            ProductCard(
                productInfo = products[index],
                onProductClick = onProductClick,
            )
        }
    }
}