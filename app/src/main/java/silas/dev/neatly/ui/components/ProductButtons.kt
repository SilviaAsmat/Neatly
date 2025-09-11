package silas.dev.neatly.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddProductButton(onProductClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onProductClick() },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun EditProductButton() {
}

@Composable
fun DeleteProductButton() {
}