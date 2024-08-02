package pl.msiwak.database.dao.user

import pl.msiwak.entities.CategoryEntity

interface ExercisesDao {
    suspend fun addCategory(categoryEntity: CategoryEntity): CategoryEntity?
    suspend fun getCategory(categoryId: String): CategoryEntity?
    suspend fun getCategories(userId: String): List<CategoryEntity>
    suspend fun removeCategory(categoryId: String)
    suspend fun addExercise(categoryEntity: CategoryEntity)
}
