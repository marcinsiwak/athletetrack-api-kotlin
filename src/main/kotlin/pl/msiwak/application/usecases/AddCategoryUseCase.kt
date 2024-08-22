package pl.msiwak.application.usecases

interface AddCategoryUseCase {
    suspend fun invoke(name: String, exerciseType: String, userId: String)
}