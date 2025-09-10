package silas.dev.neatly.ui.collections

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import silas.dev.neatly.ui.components.HeaderLabel

@Composable
fun CollectionScreen(
    viewModel: CollectionScreenViewModel

) {
    val viewState by viewModel.collectionsScreenViewState.collectAsState()
    CollectionScreen(viewState)
}

@Composable
private fun CollectionScreen(
    viewState: CollectionScreenViewState
){
    Text(text = viewState.collectionName)
}