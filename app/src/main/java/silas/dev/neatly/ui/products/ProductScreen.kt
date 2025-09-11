package silas.dev.neatly.ui.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel
) {
    val viewState by viewModel.productInfoViewState.collectAsState()
    ProductScreen(viewState)
}

@Composable
private fun ProductScreen(
    productInfo: ProductInfoViewState
) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                lineLimits = TextFieldLineLimits.SingleLine,
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(productInfo.name) }
            )
        }

    }
}
