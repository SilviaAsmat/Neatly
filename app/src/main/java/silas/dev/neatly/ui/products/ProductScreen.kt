package silas.dev.neatly.ui.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel
) {
    val viewState by viewModel.productInfoViewState.collectAsState()
    ProductScreen(
        viewState,
        viewModel::saveProduct,
        nameState = viewModel.nameLabel,
        descriptionState = viewModel.descriptionLabel)
}

@Composable
private fun ProductScreen(
    productInfo: ProductInfoViewState,
    onSaveClick: () -> Unit,
    nameState: TextFieldState,
    descriptionState: TextFieldState
) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                lineLimits = TextFieldLineLimits.SingleLine,
                state = nameState,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(productInfo.name) },
            )

            OutlinedTextField(
                lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 7),
                state = descriptionState,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(productInfo.description) }
            )

            ExtendedFloatingActionButton(
                onClick = onSaveClick,
                icon = { Icon(Icons.Filled.Save, "Extended floating action button.") },
                text = { Text(text = "Save") },
            )


        }

    }
}
