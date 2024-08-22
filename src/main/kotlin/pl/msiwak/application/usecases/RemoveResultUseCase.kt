package pl.msiwak.application.usecases

interface RemoveResultUseCase {
    suspend fun invoke(resultId: String)
}