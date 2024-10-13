package pl.asiaki.pathmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.serialization.json.Json
import pl.asiaki.pathmaster.ui.theme.Background
import pl.asiaki.pathmaster.ui.theme.Orange
import pl.asiaki.pathmaster.ui.theme.Pink
import pl.asiaki.pathmaster.ui.theme.Red
import pl.asiaki.pathmaster.ui.theme.White

@Composable
fun Center(
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content
    )
}

@Composable
fun Question(
    question: QuestionData,
    answer: Int,
    questionIndex: Int,
    questionCount: Int,
    onSelect: (Int) -> Unit,
    onMove: (Int) -> Unit,
) {
    Column {
        Column {
            Text(
                question.question
            )
            HorizontalDivider()
        }

        var selected by remember { mutableIntStateOf(-1) }
        selected = answer

        LazyColumn(
            Modifier.padding(horizontal = 0.dp, vertical = 10.dp),
        ) {
            itemsIndexed(question.answers) { i, answer ->
                Text(
                    answer,
                    Modifier.background(Color.Red)
                        .clip(RoundedCornerShape(50.dp))
                )
                RadioButton(
                    selected = selected == i,
                    onClick = {
                        selected = i
                        onSelect(selected)
                    }
                )
            }
        }

        Row {
            Button(
                onClick = {
                    onMove(-1)
                }
            ) {
                Text(
                    text = "<<",
                    fontSize = 5.em,
                    color = Red,
                )
            }

            Text(
                text = "${questionIndex + 1} / $questionCount",
                fontSize = 5.em,
                color = Red,
            )

            Button(
                onClick = {
                    onMove(1)
                }
            ) {
                Text(
                    text = ">>",
                    fontSize = 5.em,
                    color = Red,
                )
            }
        }
    }
}

class CourseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val intent = (context as CourseActivity).intent
            val courseJson = intent.getStringExtra("course")
            if (courseJson == null) {
                Center {
                    Text(
                        text = "didn't receive course data!",
                        fontSize = 5.em,
                    )
                }
                return@setContent
            }
            val course = Json.decodeFromString<CourseData>(courseJson)
            if (course.questions.isEmpty()) {
                Center {
                    Text(
                        text = "this course has no questions!",
                        fontSize = 5.em,
                    )
                }
                return@setContent
            }

            val answers = remember { mutableStateListOf(*Array(course.questions.size) { -1 }) }
            var currentQuestion by remember { mutableIntStateOf(0) }

            val onSelect: (Int) -> Unit = { index ->
                answers[currentQuestion] = index
            }

            val onMove: (Int) -> Unit = { offset ->
                if (offset >= 0) {
                    currentQuestion = (currentQuestion + offset) % answers.size
                } else if (currentQuestion + offset < 0) {
                    currentQuestion = answers.size + offset - currentQuestion
                } else {
                    currentQuestion += offset
                }
            }
            Column(Modifier.fillMaxSize().background(Background)) {
                Header(course.name)
                Question(
                    course.questions[currentQuestion],
                    answers[currentQuestion],
                    currentQuestion,
                    course.questions.size,
                    onSelect,
                    onMove
                )
            }
        }
    }
}

@Composable
fun Header(name: String) {
    val brush = Brush.linearGradient(listOf(Orange, Pink))
    Row(Modifier.padding(top = 20.dp, bottom = 10.dp).fillMaxWidth().background(brush),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) { Box(Modifier.padding(15.dp)) {
        Text(
            text = name,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 5.em,
            color = White
        )
    }
        val image = painterResource(R.drawable.burger)
        Image(
            painter = image,
            contentDescription = "burger",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(15.dp)
                .size(30.dp)
        )
    }
    Box(Modifier.padding(10.dp)) {
        Text(
            text = "QUIZ",
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Red,
            fontSize = 10.em
        )
    }
}