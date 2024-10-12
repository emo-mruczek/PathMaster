package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Profile(name: String = "Maciuś", lvl: Int = 21) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = name,
                    color = Black,
                    fontSize = 7.5.em
                )
                Text(
                    text = "Level: $lvl",
                    color = Black,
                    fontSize = 6.em
                )
            }
        val image = painterResource(R.drawable.blank_avatar)
        Image(
            painter = image,
            contentDescription = "avatar"
        )
    }
}

