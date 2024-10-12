package pl.asiaki.pathmaster

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Course(
    name: String,
    description: String,
    points: Int,
    accentColour: Color,
) {
    Column(
        Modifier.height(IntrinsicSize.Min)
            .border(
                width = 5.dp,
                color = Color.Black,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier.padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(name)

            Text("$points pkt.", Modifier.background(accentColour))
        }

        HorizontalDivider(thickness = 2.dp)

        Text(description, Modifier.padding(10.dp))
    }
}

@Preview
@Composable
fun CoursePreview() {
    Course(
        name = "Podstawy Rusta",
        description = "Ten kurs nauczy cię podstaw rusta",
        points = 5,
        accentColour = Color.Green
    )
}