package pl.asiaki.pathmaster

import kotlinx.serialization.Serializable

@Serializable
data class QuestionData(
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int,
)