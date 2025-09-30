package silas.dev.neatly.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
        ExtendedFloatingActionButton(
            onClick = onSaveClick,
            icon = { Icon(Icons.Filled.Save, "Extended floating action button.") },
            text = { Text(text = "Save") },
        )
    }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        // TODO fix text state and save/deletion for different fields
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(scrollState)) {
            GetImageFromGallery(onImageSelected) // Prompts user for image
            Text(
                text = "Product Info",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )
            DisplayProductPhoto(photoInfoViewState)
//            Box(
//                modifier = Modifier
//                    .background(MaterialTheme.colorScheme.secondaryContainer)
//                    .height(200.dp)
//                    .fillMaxWidth(.95f)
//                    .padding(16.dp)
//                    .align(Alignment.CenterHorizontally),
//            ) {
//
//            }
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
                placeholder = { Text(productInfo.name) }
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
                label = { Text(productInfo.description) },
                placeholder = { Text(productInfo.description) }
            )
        }
    }
}

