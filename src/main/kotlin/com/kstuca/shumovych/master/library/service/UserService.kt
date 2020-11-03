package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.exception.UserNotFoundException
import com.kstuca.shumovych.master.library.model.RoleModel
import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(val userRepository: UserRepository,
                  val bCryptPasswordEncoder: BCryptPasswordEncoder) : UserDetailsService {

    fun getUsers(page: Int?, size: Int?, sort: String?): List<UserModel> {
        return when {
            page != null && size != null && sort != null -> userRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).content
            page != null && size != null -> userRepository.findAll(PageRequest.of(page, size)).content
            else -> userRepository.findAll(PageRequest.of(1, 10)).content
        }
    }

    fun findUserById(id: Long): UserModel {
        val user: Optional<UserModel> = userRepository.findById(id)
        if (user.isPresent) return user.get() else throw UserNotFoundException()
    }


    fun allUsers(): MutableIterable<UserModel> = userRepository.findAll()

    fun saveUser(user: UserModel): Boolean {
        if (isUserExists(user.username!!)) return false

        val updatedUser = UserModel.from(user,
                mutableSetOf(RoleModel(1L, "ROLE_USER")),
                bCryptPasswordEncoder.encode(user.password))
        userRepository.save(updatedUser)
        return true
    }

    fun deleteUser(userId: Long): Boolean {
        if (userRepository.findById(userId).isPresent) {
            userRepository.deleteById(userId)
            return true
        }
        return false
    }

    override fun loadUserByUsername(username: String): UserModel {
        val user =  userRepository.findByUsername(username)
        if (user != null)
            return user
        else throw UserNotFoundException()
    }

    fun isUserExists(username: String) : Boolean = userRepository.findByUsername(username) != null
}