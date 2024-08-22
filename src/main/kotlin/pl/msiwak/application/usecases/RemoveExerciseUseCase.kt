package pl.msiwak.application.usecases

interface RemoveExerciseUseCase {
    suspend fun invoke(exerciseId: String)
}