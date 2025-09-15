package silas.dev.neatly.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel
) {
    val viewState by viewModel.productInfoViewState.collectAsState()
    ProductScreen(
        viewState,
        viewModel::saveProduct,
        nameState = viewModel.nameLabel,
        descriptionState = viewModel.descriptionLabel
    )
}

@Composable
private fun ProductScreen(
    productInfo: ProductInfoViewState,
    onSaveClick: () -> Unit,
    nameState: TextFieldState,
    descriptionState: TextFieldState
) {
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            onClick = onSaveClick,
            icon = { Icon(Icons.Filled.Save, "Extended floating action button.") },
            text = { Text(text = "Save") },
        )
    }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Product Info",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .height(200.dp)
                    .fillMaxWidth(.95f)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                ) {

            }
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,  // Border color when focused
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                state = nameState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(productInfo.name) },
            )

            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,  // Border color when focused
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary
                ),
                lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 7),
                state = descriptionState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(productInfo.description) }
            )


        }

    }
}

