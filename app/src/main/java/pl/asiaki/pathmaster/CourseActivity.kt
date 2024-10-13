package pl.asiaki.pathmaster

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.asiaki.pathmaster.ui.theme.Background
import pl.asiaki.pathmaster.ui.theme.Black
import pl.asiaki.pathmaster.ui.theme.Orange
import pl.asiaki.pathmaster.ui.theme.Pink
import pl.asiaki.pathmaster.ui.theme.Red
import pl.asiaki.pathmaster.ui.theme.Salmon
import pl.asiaki.pathmaster.ui.theme.White

@Composable
fun Center(
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content,
    )
}

@Composable
fun Question(
    question: QuestionData,
    answer: Int,
    onSelect: (Int) -> Unit,
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            Modifier.padding(7.dp)
        ) {
            Text(
                text = question.question,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 7.em,
            )

            HorizontalDivider()
        }

        var selected by remember { mutableIntStateOf(-1) }
        selected = answer

        LazyColumn(
            Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            itemsIndexed(question.answers) { i, answer ->
                Column(
                    modifier = Modifier
                        .padding(7.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            if (selected == i) {
                               Brush.linearGradient(listOf(Orange, Pink))
                            } else {
                               Brush.linearGradient(listOf(Salmon, Salmon))
                            }
                        )
                        .clickable {
                            selected = i
                            onSelect(selected)
                        }
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = answer,
                        fontSize = 6.em,
                        modifier = Modifier.padding(5.dp),
                        color = Black,
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionPicker(
    questionIndex: Int,
    questionCount: Int,
    allQuestionsAnswered: Boolean,
    onMove: (Int) -> Unit,
    onFinish: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (allQuestionsAnswered) {
            Button(
                onClick = onFinish,
                colors = ButtonColors(
                    contentColor = White,
                    containerColor = Red,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black,
                )
            ) {
                Text(
                    text = "Zakończ quiz",
                    fontSize = 5.em,
                )
            }
        }
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    onMove(-1)
                },
                colors = ButtonColors(
                    contentColor = White,
                    containerColor = Red,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black,
                )
            ) {
                Text(
                    text = "<<",
                    fontSize = 5.em,
                )
            }

            Text(
                text = "${questionIndex + 1} / $questionCount",
                fontSize = 5.em,
                color = Red,
                fontFamily = robotoFamily,
                fontStyle = FontStyle.Normal,
            )

            Button(
                onClick = {
                    onMove(1)
                },
                colors = ButtonColors(
                    contentColor = White,
                    containerColor = Red,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black,
                )
            ) {
                Text(
                    text = ">>",
                    fontSize = 5.em,
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
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Normal,
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
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Normal,
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

            val onFinish: () -> Unit = {
                // TODO: submit question data to server
                val finishedIntent = Intent(context, MainActivity::class.java)
                val passedCourse = course.questions.withIndex()
                    .all { q ->
                        val question = q.value
                        val answer = answers[q.index]
                        question.correctAnswer == answer
                    }
                finishedIntent.putExtra("passed", passedCourse)
                finishedIntent.putExtra("course", Json.encodeToString(course))
                context.startActivity(finishedIntent)
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Background),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Header(course.name)
                    Question(
                        course.questions[currentQuestion],
                        answers[currentQuestion],
                        onSelect,
                    )
                }
                QuestionPicker(
                    currentQuestion,
                    course.questions.size,
                    answers.all { answer -> answer != -1 },
                    onMove,
                    onFinish,
                )
            }
        }
    }
}

@Composable
fun Header(name: String) {
    Row(
        Modifier
            .padding(top = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .background(Brush.linearGradient(listOf(Orange, Pink))),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            Modifier.padding(15.dp)
        ) {
            Text(
                text = name,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 5.em,
                color = White,
            )
        }
        Image(
            painter = painterResource(R.drawable.burger),
            contentDescription = "burger",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(15.dp)
                .size(30.dp),
        )
    }
    Box(
        Modifier.padding(10.dp)
    ) {
        Text(
            text = "QUIZ",
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Red,
            fontSize = 10.em,
        )
    }
}