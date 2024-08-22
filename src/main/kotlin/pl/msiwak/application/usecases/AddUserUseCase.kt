package pl.msiwak.application.usecases

interface AddUserUseCase {
    suspend fun invoke(name: String, email: String, userId: String)
}