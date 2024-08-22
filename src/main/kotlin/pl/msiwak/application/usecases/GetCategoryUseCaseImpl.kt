package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.domain.repositories.ExerciseRepository

class GetCategoryUseCaseImpl(private val exerciseRepository: ExerciseRepository) : GetCategoryUseCase {
    override suspend fun invoke(id: String): CategoryDTO? {
        val categoryEntity = exerciseRepository.getCategory(id) ?: return null
        return with(categoryEntity) {
            CategoryDTO(
                id = id,
                name = name,
                exerciseType = exerciseType
            )
        }
    }
}