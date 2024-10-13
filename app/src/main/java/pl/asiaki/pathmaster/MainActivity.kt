package pl.asiaki.pathmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.json.Json

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

            //TODO: check whether key is already on device


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