package pl.asiaki.pathmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import kotlinx.serialization.json.Json
import pl.asiaki.pathmaster.ui.theme.Background
import pl.asiaki.pathmaster.ui.theme.Pink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val courses: MutableList<CourseData> = COURSES.toMutableList()

        setContent {
            val context = LocalContext.current
            val intent = (context as MainActivity).intent
            val courseJson = intent.getStringExtra("course")
            if (courseJson != null) {
                val course = Json.decodeFromString<CourseData>(courseJson)
                val passed = intent.getBooleanExtra("passed", false)
                if (passed) {
                    courses.remove(course)
                }
            }

            Box(
                Modifier.fillMaxSize().background(Pink)
            ) {
                Profile(
                    UserData(
                        name = "Macius",
                        surname = "Maciusiowy",
                        coursesInProgress = COURSES,
                        lvl = 21,
                        MsT = 2137,
                    ),
                    courses
                )
            }
        }
    }
}