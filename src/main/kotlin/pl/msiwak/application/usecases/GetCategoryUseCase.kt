package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO

interface GetCategoryUseCase {
    suspend fun invoke(id: String): CategoryDTO?
}