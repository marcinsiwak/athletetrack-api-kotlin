package pl.msiwak.application.usecases

interface AddExerciseUseCase {
    suspend fun invoke(categoryId: String, name: String)
}