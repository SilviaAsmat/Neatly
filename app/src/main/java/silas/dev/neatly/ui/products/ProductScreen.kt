package silas.dev.neatly.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import silas.dev.neatly.ui.components.GetImageFromGallery
import silas.dev.neatly.ui.components.photo_picker.DisplayProductPhoto

@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel
) {
    val viewState by viewModel.productInfoViewState.collectAsState()
    val photoInfoViewState by viewModel.photoInfoViewState.collectAsState()

    ProductScreen(
        viewState,
        viewModel::saveProduct,
        nameState = viewModel.nameLabel,
        descriptionState = viewModel.descriptionLabel,
        onImageSelected = viewModel::setPhoto,
        photoInfoViewState = photoInfoViewState,
    )
}

@Composable
private fun ProductScreen(
    productInfo: ProductInfoViewState,
    onSaveClick: () -> Unit,
    nameState: TextFieldState,
    descriptionState: TextFieldState,
    onImageSelected: (uri: String) -> Unit,
    photoInfoViewState: PhotoInfoViewState,
) {
    Scaffold(floatingActionButton = {
        Row(modifier = Modifier.padding(8.dp),horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GetImageFromGallery(onImageSelected) // Prompts user for image,
            ExtendedFloatingActionButton(
                onClick = onSaveClick,
                icon = { Icon(Icons.Filled.Save, "Extended floating action button.") },
                text = { Text(text = "Save") }
            )
        }
    }, modifier = Modifier.padding(8.dp),
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        // TODO fix text state and save/deletion for different fields
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Product Info",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            DisplayProductPhoto(photoInfoViewState)
            OutlinedTextField(
                lineLimits = TextFieldLineLimits.SingleLine,
                state = nameState,
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(productInfo.name) },
                placeholder = { Text(productInfo.name) }
            )

            OutlinedTextField(
                lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 7),
                state = descriptionState,
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(productInfo.description) },
                placeholder = { Text(productInfo.description) }
            )
        }
    }
}

