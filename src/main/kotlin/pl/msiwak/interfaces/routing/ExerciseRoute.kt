package pl.msiwak.interfaces.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.application.usecases.*
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO
import pl.msiwak.application.usecases.GetCategoriesUseCase
import pl.msiwak.application.usecases.GetCategoryUseCase
import pl.msiwak.application.usecases.GetExerciseUseCase

fun Route.addExerciseRoute() {
    val addCategoryUseCase by inject<AddCategoryUseCase>()
    val addExerciseUseCase by inject<AddExerciseUseCase>()
    val addResultUseCase by inject<AddResultUseCase>()
    val getCategoriesUseCase by inject<GetCategoriesUseCase>()
    val getCategoryUseCase by inject<GetCategoryUseCase>()
    val getExerciseUseCase by inject<GetExerciseUseCase>()
    val removeCategoryUseCase by inject<RemoveCategoryUseCase>()
    val removeExerciseUseCase by inject<RemoveExerciseUseCase>()
    val removeResultUseCase by inject<RemoveResultUseCase>()

    authenticate(FIREBASE_AUTH) {
        get("/categories") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@get respond(HttpStatusCode.Unauthorized)
                getCategoriesUseCase.invoke(principal.userId).run { respond(HttpStatusCode.OK, this) }
            }
        }

        post("/category") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post respond(HttpStatusCode.Unauthorized)
                receive<CategoryDTO>().run {
                    addCategoryUseCase.invoke(name, exerciseType, principal.userId)
                    respond(HttpStatusCode.OK, this)
                }
            }
        }

        get("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                getCategoryUseCase.invoke(id)?.let { respond(HttpStatusCode.OK, it) } ?: return@get call.respond(
                    HttpStatusCode.NotFound
                )
            }
        }

        delete("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                removeCategoryUseCase.invoke(id)
                respond(HttpStatusCode.OK)
            }
        }

        post("/exercise") {
            with(call) {
                receive<ExerciseDTO>().run {
                    addExerciseUseCase.invoke(categoryId, name)
                    respond(HttpStatusCode.OK, this)
                }
            }
        }

        get("/exercise/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@get respond(HttpStatusCode.BadRequest)
                getExerciseUseCase.invoke(id)?.let { respond(HttpStatusCode.OK, it) } ?: respond(HttpStatusCode.NotFound)
            }
        }

        delete("/exercise/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@delete respond(HttpStatusCode.BadRequest)
                removeExerciseUseCase.invoke(id)
                respond(HttpStatusCode.OK)
            }
        }

        post("/result") {
            with(call) {
                receive<ResultDTO>().run {
                    addResultUseCase.invoke(
                        exerciseId = exerciseId,
                        amount = amount,
                        result = result,
                        date = date
                    )
                    respond(HttpStatusCode.OK, this)
                }
            }
        }

        delete("/result/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@delete respond(HttpStatusCode.BadRequest)
                removeResultUseCase.invoke(id)
                respond(HttpStatusCode.OK)
            }
        }
    }
}
