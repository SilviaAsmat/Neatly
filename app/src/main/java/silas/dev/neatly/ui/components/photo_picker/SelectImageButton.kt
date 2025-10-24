package silas.dev.neatly.ui.components.photo_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import silas.dev.neatly.ui.components.rememberGalleryThumbnail

/**
 * A composable function that creates a button with an icon for selecting an image from the gallery.
 *
 * @param onClick The lambda expression that will be invoked when the button is clicked.
 */
@Composable
fun SelectImageButton(onClick: () -> Unit) {

    // Create a Button with the specified onClick lambda
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(16.dp),
    ) {
        // Create a Row to arrange the icon and text horizontally
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Display an icon using the rememberGalleryThumbnail() function
            Icon(
                imageVector = rememberGalleryThumbnail(),
                contentDescription = "Gallery Icon"
            )
            // Add spacing between the icon and the text
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            // Display text for the button
            Text(text = "Select image from Gallery")
        }
    }
}