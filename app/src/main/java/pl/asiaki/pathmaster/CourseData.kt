package pl.asiaki.pathmaster

import kotlinx.serialization.Serializable

@Serializable
data class CourseData(
    val name: String,
    val description: String,
    val points: UInt,
    val questions: List<QuestionData>,
)
