package silas.dev.neatly.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@ExperimentalMaterial3ExpressiveApi
@Composable
fun AddCollectionButton(onValueChange: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { showDialog = true },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
    ) {
        Icon(Icons.Filled.Add, "Floating action button to add collection.")
    }
    if (showDialog) {
        var text by remember { mutableStateOf("") } // Local state for the TextField
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("New Collection") },
            text = {
                TextField(
                    value = text, // Controlled by the local 'text' state
                    onValueChange = { newText ->
                        text = newText // Update local state as user types
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onValueChange(text) // Call onValueChange with the final text
                        showDialog = false    // Close the dialog
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Dismiss")
                }
            }
        )
    }
}
