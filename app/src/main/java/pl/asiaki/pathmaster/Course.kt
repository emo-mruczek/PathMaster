package pl.asiaki.pathmaster

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class CourseLevel(val upperPointBound: UInt, val colour: Color) {
    BASIC(100u, Color.Blue),
    EASY(500u, Color.Green),
    MEDIUM(1500u, Color.Yellow),
    HARD(5000u, Color.Red),
    EXTREME(UInt.MAX_VALUE, Color.Magenta);

    companion object {
        fun level(points: UInt): CourseLevel {
            return if (points <= BASIC.upperPointBound)    { BASIC }
                else if (points <= EASY.upperPointBound)   { EASY }
                else if (points <= MEDIUM.upperPointBound) { MEDIUM }
                else if (points <= HARD.upperPointBound)   { HARD }
                else                                       { EXTREME }
        }
    }
}

@Serializable
data class CourseData(
    val name: String,
    val description: String,
    val points: UInt,
    val questions: List<QuestionData>,
)

val COURSES = listOf(
    CourseData(
        name = "Podstawy Rusta",
        description = "Ten kurs nauczy cię podstaw rusta",
        points = 250u,
        questions = listOf(),
    ),
    CourseData(
        name = "Wprowadzenie do Haskella",
        description = "Boisz się programowania funkcyjnego? Ten kurs jest dla ciebie!",
        points = 550u,
        questions = listOf(),
    ),
    CourseData(
        name = "HTML dla bystrzaków",
        description = "Poznaj sztukę pisania stron razem z tym kursem",
        points = 30u,
        questions = listOf(),
    ),
    CourseData(
        name = "Wskaźniki na funkcję w C",
        description = "Tylko dla odważnych!",
        points = 10000u,
        questions = listOf(),
    ),
    CourseData(
        name = "Instalacja Arch Linuxa",
        description = "I use Arch BTW!",
        points = 2000u,
        questions = listOf(),
    ),
)

@Composable
fun Course(
    course: CourseData,
) {
    val context = LocalContext.current

    Column(
        Modifier
            .height(IntrinsicSize.Min)
            .border(
                width = 5.dp,
                color = Color.Black,
            )
            .clickable {
                val intent = Intent(context, CourseActivity::class.java)
                intent.putExtra("course", Json.encodeToString(course))
                context.startActivity(intent)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(course.name)

            Text("${course.points} pkt.", Modifier.background(CourseLevel.level(course.points).colour))
        }

        HorizontalDivider(thickness = 2.dp)

        Text(course.description, Modifier.padding(10.dp))
    }
}

@Preview
@Composable
fun CoursePreview() {
    Course(COURSES[0])
}