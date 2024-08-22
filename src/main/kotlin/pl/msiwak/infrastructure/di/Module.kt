package pl.msiwak.infrastructure.di

import org.koin.dsl.module
import pl.msiwak.application.usecases.*
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.domain.repositories.UserRepository
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDao
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDaoImpl
import pl.msiwak.infrastructure.database.dao.user.UserDao
import pl.msiwak.infrastructure.database.dao.user.UserDaoImpl

val diModule = module {
    single<AddUserUseCase> { AddUserUseCaseImpl(get()) }
    single<AddCategoryUseCase> { AddCategoryUseCaseImpl(get()) }
    single<AddExerciseUseCase> { AddExerciseUseCaseImpl(get()) }
    single<AddResultUseCase> { AddResultUseCaseImpl(get()) }
    single<GetUserUseCase> { GetUserUseCaseImpl(get()) }
    single<GetCategoryUseCase> { GetCategoryUseCaseImpl(get()) }
    single<GetExerciseUseCase> { GetExerciseUseCaseImpl(get()) }
    single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
    single<RemoveCategoryUseCase> { RemoveCategoryUseCaseImpl(get()) }
    single<RemoveExerciseUseCase> { RemoveExerciseUseCaseImpl(get()) }
    single<RemoveResultUseCase> { RemoveResultUseCaseImpl(get()) }
    single { UserRepository(get()) }
    single { ExerciseRepository(get()) }
    single<UserDao> { UserDaoImpl() }
    single<ExercisesDao> { ExercisesDaoImpl() }
}