package pl.asiaki.pathmaster

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val name: String,
    val surname: String,
    val coursesInProgress: List<CourseData>,
    val lvl: Int,
    val MsT: Int
)
