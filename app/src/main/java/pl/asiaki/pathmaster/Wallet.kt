package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.asiaki.pathmaster.ui.theme.Orange
import pl.asiaki.pathmaster.ui.theme.Pink
import pl.asiaki.pathmaster.ui.theme.White

@Composable
fun WalletComposable(amount: UInt) {
    val brush = Brush.linearGradient(listOf(Orange, Pink))
    Column(Modifier.clip(RoundedCornerShape(20.dp)).background(brush).padding(5.dp)) {

        Row(
            Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = "PORTMONETKA",
                color = White,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp
            )
            //TODO: buttor
            /*Text(
                text = "Sprawdz ->",
                color = White,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )*/
        }
        Row(
            Modifier.padding(10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val image = painterResource(R.drawable.hat_white)
            Image(
                painter = image,
                contentDescription = "MsT icon",
                modifier = Modifier.padding(10.dp)
                    .size(100.dp)

            )
            //TODO: buttor
            Text(
                text = "$amount",
                color = White,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp
            )
            Text(
                text = "MsT",
                color = White,
                fontSize = 25.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun WalletComposablePreview(){
    WalletComposable(2137u)
}
