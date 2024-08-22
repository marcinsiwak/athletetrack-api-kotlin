package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO

interface GetCategoriesUseCase {
    suspend fun invoke(userId: String): List<CategoryDTO>
}