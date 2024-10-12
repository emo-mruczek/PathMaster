package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.asiaki.pathmaster.ui.theme.Orange
import pl.asiaki.pathmaster.ui.theme.Pink
import pl.asiaki.pathmaster.ui.theme.White

@Composable
fun WalletComposable(amount: UInt) {
    val brush = Brush.linearGradient(listOf(Orange, Pink))
    Box(Modifier.background(brush)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "WALLET",
                color = White
            )
            //TODO: buttor
            Text(
                text = "Check balance ->",
                color = White
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val image = painterResource(R.drawable.hat)
            Image(
                painter = image,
                contentDescription = "MsT icon",
                modifier = Modifier
                    .size(100.dp)
                    .padding(20.dp)
            )
            //TODO: buttor
            Text(
                text = "$amount",
                color = White
            )
            Text(
                text = "MsT",
                color = White
            )
        }
    }
}

@Preview
@Composable
fun WalletComposablePreview(){
    WalletComposable(2137u)
}
