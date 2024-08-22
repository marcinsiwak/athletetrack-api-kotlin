package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO
import pl.msiwak.domain.repositories.ExerciseRepository

class GetExerciseUseCaseImpl(private val exerciseRepository: ExerciseRepository) : GetExerciseUseCase {
    override suspend fun invoke(id: String): ExerciseDTO? {
        val category = exerciseRepository.getCategoryByExercise(id) ?: return null
        val exercise = category.getExercise(id) ?: return null
        return with(exercise) {
            ExerciseDTO(
                id = id,
                categoryId = categoryId!!,
                name = name,
                exerciseType = category.exerciseType,
                results = exercise.results.map {
                    ResultDTO(
                        id = it.id,
                        exerciseId = exercise.id!!,
                        amount = it.amount,
                        result = it.result,
                        date = it.date
                    )
                }
            )
        }
    }
}