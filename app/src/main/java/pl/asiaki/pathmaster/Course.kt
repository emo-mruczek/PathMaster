package pl.asiaki.pathmaster

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.asiaki.pathmaster.ui.theme.Black
import pl.asiaki.pathmaster.ui.theme.Green
import pl.asiaki.pathmaster.ui.theme.Orange
import pl.asiaki.pathmaster.ui.theme.Purple40
import pl.asiaki.pathmaster.ui.theme.Red
import pl.asiaki.pathmaster.ui.theme.Salmon

enum class CourseLevel(val upperPointBound: UInt, val colour: Color) {
    BASIC(100u, Purple40),
    EASY(500u, Green),
    MEDIUM(1500u, Orange),
    HARD(5000u, Red),
    EXTREME(UInt.MAX_VALUE, Black);

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
        questions = listOf(
            QuestionData(
                question = "Co to jest Rust?",
                answers = listOf("To taki język programowania", "Nie wiem", "To system operacyjny"),
                correctAnswer = 2,
            ),
            QuestionData(
                question = "Który fragment kodu poprawnie wypisze tekst?",
                answers = listOf("println!(\"Hello World!\");", "println!(\"Hello World!);", "print(\"Hello World!\");"),
                correctAnswer = 0,
            ),
            QuestionData(
                question = "Jaki jest najlepszy język programowania?",
                answers = listOf("Rust", "Rust", "Rust!!!"),
                correctAnswer = 2,
            ),
        ),
    ),
    CourseData(
        name = "Wprowadzenie do Haskella",
        description = "Boisz się programowania funkcyjnego? Ten kurs jest dla ciebie!",
        points = 550u,
        questions = listOf(
            QuestionData(
                question = "Czy boisz się Haskella?",
                answers = listOf("Tak", "Nie"),
                correctAnswer = 0,
            ),
            QuestionData(
                question = "Ile jest typów całkowitych w Haskellu?",
                answers = listOf("Jeden", "Dwa", "Zero"),
                correctAnswer = 1,
            ),
            QuestionData(
                question = "Kiedy powstał Haskell?",
                answers = listOf("2020", "1990", "1781"),
                correctAnswer = 1,
            ),
        ),
    ),
    CourseData(
        name = "HTML dla bystrzaków",
        description = "Poznaj sztukę pisania stron razem z tym kursem",
        points = 30u,
        questions = listOf(
            QuestionData(
                question = "Czy HTML jest językiem programowania?",
                answers = listOf("Tak", "Nie", "To zależy"),
                correctAnswer = 1,
            ),
            QuestionData(
                question = "Co robi tag <marquee>?",
                answers = listOf("Pozwala na animację tekstu, grafiki, bądź obrazów", "Nic nie robi", "Pogrubia zawarty w nim tekst"),
                correctAnswer = 0,
            ),
            QuestionData(
                question = "Rozwiń skrót HTML",
                answers = listOf("HyperText Markdown Language", "Hiperaktywne Trolle Malują Lamy", "HyperText Markup Language"),
                correctAnswer = 2,
            ),
        ),
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
        questions = listOf(
            QuestionData(
                question = "Co to jest AUR?",
                answers = listOf("To takie zwierzę", "Nie wiem", "Arch User Repository", "Jest to kryptowaluta"),
                correctAnswer = 2,
            ),
            QuestionData(
                question = "Jakie zwierzę jest maskotką Linuksa?",
                answers = listOf("Pingwin", "Żyrafa", "Jeleń"),
                correctAnswer = 0,
            ),
            QuestionData(
                question = "Jakiego menadżera paczek używa Arch?",
                answers = listOf("APT", "Manpac", "Pacman"),
                correctAnswer = 2,
            ),
        ),
    ),
)

@Composable
fun Course(
    course: CourseData,
) {
    val context = LocalContext.current
    Column(
        Modifier
            .height(IntrinsicSize.Min).padding(5.dp)
            .clickable {
                val intent = Intent(context, CourseActivity::class.java)
                intent.putExtra("course", Json.encodeToString(course))
                context.startActivity(intent)
            }.clip(RoundedCornerShape(20.dp)).background(Salmon),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(course.name,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp,
                color = Red
                )
            Row(
                Modifier.padding(3.dp).clip(RoundedCornerShape(15.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "${course.points} pkt.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = CourseLevel.level(course.points).colour,
                )
            }
        }

        HorizontalDivider(thickness = 2.dp)

        Text(course.description, Modifier.padding(10.dp),
            fontSize = 4.em
        )
    }
}

@Preview
@Composable
fun CoursePreview() {
    Course(COURSES[0])
}
