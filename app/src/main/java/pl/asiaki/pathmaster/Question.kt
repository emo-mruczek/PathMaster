package pl.asiaki.pathmaster

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: UInt,
)