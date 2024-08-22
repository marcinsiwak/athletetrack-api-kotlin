package pl.msiwak.application.usecases

interface RemoveCategoryUseCase {
    suspend fun invoke(categoryId: String)
}