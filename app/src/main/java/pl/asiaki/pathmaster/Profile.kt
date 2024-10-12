package pl.asiaki.pathmaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Profile(name: String, lvl: Int) {
    Column(Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = name,
                    color = Black,
                    fontSize = 7.5.em,
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
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(200.dp))
            )
        }
        Column(Modifier.fillMaxWidth().padding(top = 25.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            CoursesList();
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

@Composable
fun CoursesList() {
    Course(
        name = "Podstawy Rusta",
        description = "Ten kurs nauczy cię podstaw rusta",
        points = 5,
        accentColour = Color.Green
    )
    Course(
        name = "Podstawy Rusta",
        description = "Ten kurs nauczy cię podstaw rusta",
        points = 5,
        accentColour = Color.Green
    )
    Course(
        name = "Podstawy Rusta",
        description = "Ten kurs nauczy cię podstaw rusta",
        points = 5,
        accentColour = Color.Green
    )
}