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
import pl.msiwak.application.usecases.AddUserUseCase
import pl.msiwak.interfaces.dtos.UserDTO
import pl.msiwak.application.usecases.GetUserUseCase

fun Route.addUserRoutes() {
    val addUserUseCase by inject<AddUserUseCase>()
    val getUserUseCase by inject<GetUserUseCase>()

    authenticate(FIREBASE_AUTH) {
        post("/user") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                receive<UserDTO>().run { addUserUseCase.invoke(name, email, principal.userId) }
                respond(HttpStatusCode.OK)
            }
        }

        get("/user") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                getUserUseCase.invoke(principal.userId)?.let {
                    respond(status = HttpStatusCode.OK, message = it)
                } ?: return@get call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
