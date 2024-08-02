package pl.msiwak.dtos

import kotlinx.serialization.Serializable
import pl.msiwak.util.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class ExerciseDTO(
    val id: String? = null,
    val categoryId: String,
    val name: String,
    val exerciseType: String
//    val results: List<ResultDTO>,
)
