package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.ExerciseDTO

interface GetExerciseUseCase {
    suspend fun invoke(id: String): ExerciseDTO?
}