package silas.dev.neatly.ui.components.photo_picker

import android.net.Uri
import androidx.compose.runtime.Composable

import silas.dev.neatly.ui.theme.dimen_10dp
import silas.dev.neatly.ui.theme.dimen_14dp
import silas.dev.neatly.ui.theme.dimen_16dp
import silas.dev.neatly.ui.theme.dimen_18dp
import silas.dev.neatly.ui.theme.dimen_200dp
import silas.dev.neatly.ui.theme.dimen_5dp
import silas.dev.neatly.ui.utils.UriUtils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import silas.dev.neatly.ui.products.PhotoInfoViewState
import androidx.core.net.toUri


@Composable
fun DisplayProductPhoto(photoInfo: PhotoInfoViewState) {
    val context = LocalContext.current
    // Create a remembered variable to store the loaded image bitmap
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

//    // Create a remembered variable to track whether an image is loaded
    var isImageLoaded by remember { mutableStateOf(false) }

    // Convert the URI to a Bitmap and set it as the imageBitmap
    val uri: Uri = photoInfo.uri.toUri()
    imageBitmap = UriUtils().uriToBitmap(context, uri)?.asImageBitmap()

    // Set isImageLoaded to true
    isImageLoaded = true

    // Create a Card composable to wrap the image and button
    Card(
        shape = RoundedCornerShape(dimen_14dp),
        modifier = Modifier.padding(dimen_10dp, dimen_16dp, dimen_10dp, dimen_16dp),
        elevation = CardDefaults.cardElevation(defaultElevation = dimen_10dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
//        // Create a Column to arrange the image and button vertically
        Column {
            // Check if an image is loaded
            if (isImageLoaded) {
                // Display the loaded image using the Image composable
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(dimen_200dp)
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = dimen_18dp,
                                    bottomEnd = dimen_18dp
                                )
                            )
                    )
                }
            }

            // Add spacing
            Spacer(modifier = Modifier.height(dimen_5dp))


        }
    }
}