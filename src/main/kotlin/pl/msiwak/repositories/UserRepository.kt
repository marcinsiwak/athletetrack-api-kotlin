package pl.msiwak.repositories

import pl.msiwak.database.dao.user.UserDao
import pl.msiwak.entities.UserEntity

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(id: String, name: String, email: String) {
        userDao.addNewUser(id, name, email)
    }

    suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }
}
