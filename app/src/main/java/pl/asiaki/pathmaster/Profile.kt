package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun Profile() {
    val image = painterResource(R.drawable.blank_avatar)
    Image(
        painter = image,
        contentDescription = "avatar"
    )
}

