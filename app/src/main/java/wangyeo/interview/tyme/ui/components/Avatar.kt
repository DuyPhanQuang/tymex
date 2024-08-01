package wangyeo.interview.tyme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Avatar(
    imageUrl: String,
    sizeInDp: Float
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = Modifier.size(sizeInDp.dp)
    )
}

@Composable
fun XSAvatar(
    imageUrl: String
) {
    Avatar(imageUrl, 32f)
}

@Composable
fun SMAvatar(
    imageUrl: String
) {
    Avatar(imageUrl, 64f)
}

@Composable
fun MDAvatar(
    imageUrl: String
) {
    Avatar(imageUrl, 128f)
}

@Composable
fun LGAvatar(
    imageUrl: String
) {
    Avatar(imageUrl, 256f)
}

@Composable
fun XLAvatar(
    imageUrl: String
) {
    Avatar(imageUrl, 512f)
}