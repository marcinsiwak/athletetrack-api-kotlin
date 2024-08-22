package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.UserDTO
import pl.msiwak.domain.repositories.UserRepository

class GetUserUseCaseImpl(
    private val userRepository: UserRepository,
) : GetUserUseCase {
    override suspend fun invoke(userId: String): UserDTO? {
        // add mapper here
        val userEntity = userRepository.getUser(userId)
        userEntity?.let {
            return UserDTO(
                id = it.id,
                email = it.email,
                name = it.name
            )
        }
        return null
    }
}