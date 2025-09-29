package silas.dev.neatly.ui.components

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import silas.dev.neatly.ui.components.photo_picker.SelectImageButton

@Composable
fun GetImageFromGallery(onImageSelected: (uri: String) -> Unit) {
    // Retrieve the current context using LocalContext.current
    val context = LocalContext.current
    // Create an activity result launcher for picking visual media (images in this case)
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let { selectedUri -> // Renamed for clarity within the block
                try {
                    val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    context.contentResolver.takePersistableUriPermission(selectedUri, flag)
                    onImageSelected(selectedUri.toString())
                } catch (e: SecurityException) {
                    Log.e("GetImageFromGallery", "Failed to take persistable URI permission", e)
                    // Consider how to handle this error in terms of onImageSelected
                }
            }
        }
    // Display a button with an icon to trigger the image selection
    SelectImageButton(
        // Handles the click event by launching the image selection activity
        onClick = { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
    )
}

