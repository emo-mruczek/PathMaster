package pl.asiaki.pathmaster

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WalletComposable(amount: UInt) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "WALLET"
                )
                //TODO: buttor
                Text(
                    text = "Check balance ->"
                )
    }
}

@Preview
@Composable
fun WalletComposablePreview(){
    WalletComposable(2137u)
}
