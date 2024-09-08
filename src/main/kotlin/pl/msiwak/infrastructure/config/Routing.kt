package pl.msiwak.infrastructure.config

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*
import pl.msiwak.interfaces.routing.addExerciseRoute
import pl.msiwak.interfaces.routing.addUserRoutes

fun Application.configureRouting() {
    routing {
        get {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title { +"Welcome Page" }
                }
                body {
                    h1 { +"Bartek odpisz proszeee" }
                    p { +"Bartek odpisz proszeeeee" }
                    a(href = "https://ktor.io") { +"Learn more about Ktor" }
                }
            }
        }
        route("/api") {
            addUserRoutes()
            addExerciseRoute()
        }
    }
}
