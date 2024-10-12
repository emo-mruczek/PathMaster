package pl.asiaki.pathmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json

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
                selected = false,
                onClick = {
                    onSelect(i)
                }
            )
        }
    }
}

class CourseActivity : ComponentActivity() {
    private var answers: Array<Int> = arrayOf()
    private var currentQuestion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val intent = (context as CourseActivity).intent
            val courseJson = intent.getStringExtra("course")
            if (courseJson == null) {
                Text("didn't receive course data1")
                return@setContent
            }
            val course = Json.decodeFromString<CourseData>(courseJson)
            if (course.questions.isEmpty()) {
                Text("course has no questions!")
                return@setContent
            }

            val onSelect: (Int) -> Unit = { index ->
                answers[currentQuestion] = index
            }

            Question(course.questions[currentQuestion], onSelect)
        }
    }
}