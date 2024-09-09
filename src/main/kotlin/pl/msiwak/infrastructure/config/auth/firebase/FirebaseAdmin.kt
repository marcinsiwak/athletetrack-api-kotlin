package pl.msiwak.infrastructure.config.auth.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.InputStream

object FirebaseAdmin {
    private val serviceAccount: InputStream? = setServiceAccount()

    private val options: FirebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    private fun setServiceAccount(): InputStream {
        println("MYLOG: Firebase setServiceAccount")
        val one = System.getenv("FIREBASE_SERVICE_ACCOUNT")?.byteInputStream()
        println("MYLOG: Firebase setServiceAccount: $one")
        return one
            ?: this::class.java.classLoader.getResourceAsStream("sportplatform-b5318-firebase-adminsdk-egpiw-0065c30f75.json")
            ?: throw IllegalStateException("Firebase service account not found in environment variables or resources")

    }

    fun init(): FirebaseApp {
        return FirebaseApp.getApps().firstOrNull() ?: FirebaseApp.initializeApp(options)
    }}
