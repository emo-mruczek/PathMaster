package pl.asiaki.pathmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.serialization.json.Json

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
    onSelect: (Int) -> Unit,
) {
    Column {
        Text(
            question.question
        )
        HorizontalDivider()
    }

    var selected by remember { mutableIntStateOf(-1) }

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
                    onSelect(i)
                    selected = i
                }
            )
        }
    }
}

class CourseActivity : ComponentActivity() {
    private var answers: IntArray = intArrayOf()
    private var currentQuestion: Int = 0

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
                        text = "course has no questions!",
                        fontSize = 5.em,
                    )
                }
                return@setContent
            }

            answers = IntArray(course.questions.size) { -1 }

            val onSelect: (Int) -> Unit = { index ->
                answers[currentQuestion] = index
            }

            Question(course.questions[currentQuestion], onSelect)
        }
    }
}