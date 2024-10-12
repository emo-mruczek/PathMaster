package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Profile(name: String, lvl: Int) {
    Column(
        Modifier.fillMaxWidth().
            padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    color = Black,
                    fontSize = 7.5.em,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Level: $lvl",
                    color = Black,
                    fontSize = 6.em
                )
            }
            val image = painterResource(R.drawable.pfp_new)
            Image(
                painter = image,
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(200.dp))
            )
        }
        WalletComposable(VALUE)
        Row(
            Modifier.padding(top = 20.dp, bottom = 10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "OSTATNIE KURSY",
                color = Black,
                fontSize = 6.5.em,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Light
            )
            //TODO: buttor
            Text(
                text = "Więcej ->",
                color = Black,
                fontSize = 3.5.em,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold
            )
        }
        CoursesList(COURSES)
    }
}

@Composable
fun CoursesList(courses: List<CourseData>) {
    LazyColumn(
        Modifier.padding(horizontal = 0.dp, vertical = 10.dp),
    ) {
        items(courses) { course ->
            Course(course)
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile(
        name = "Maciuś",
        lvl = 21,
    )
}

val VALUE = 2137u