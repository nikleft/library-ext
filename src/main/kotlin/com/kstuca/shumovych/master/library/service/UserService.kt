package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.exception.UserNotFoundException
import com.kstuca.shumovych.master.library.model.RoleModel
import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
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

    fun registerUser(user: UserModel, role: RoleModel): Boolean {
        if (user.username != null && isUserExists(user.username!!)) return false

        val updatedUser = UserModel.from(user,
                mutableSetOf(role),
                bCryptPasswordEncoder.encode(user.password))
        userRepository.save(updatedUser)
        return true
    }

    fun updateCurrentUser(user: UserModel): UserModel {
        val authentication: Authentication = UsernamePasswordAuthenticationToken(user, user.password, user.authorities)
        SecurityContextHolder.getContext().authentication = authentication
        return userRepository.save(user)
    }

    fun changeUser(oldUser: UserModel, newUser: UserModel): UserModel = UserModel(
            id = oldUser.id,
            name = newUser.name ?: oldUser.name,
            favouriteGenres = newUser.favouriteGenres ?: oldUser.favouriteGenres,
            email = newUser.email ?: oldUser.email,
            address = newUser.address ?: oldUser.address,
            phone = newUser.phone ?: oldUser.phone,
            surname = newUser.surname ?: oldUser.surname,

            username = newUser.username ?: oldUser.username,
            password = if (newUser.password != null) bCryptPasswordEncoder.encode(newUser.password) else oldUser.password,
            books = oldUser.books,
            reviews = oldUser.reviews,
            friends = newUser.friends ?: oldUser.friends,
            registrationDate = oldUser.registrationDate,
            recommendationType = newUser.recommendationType ?: oldUser.recommendationType,
            image = newUser.image ?: oldUser.image,
            roles = oldUser.roles
    )

    fun updateUser(oldUser: UserModel, newUser: UserModel): UserModel {
        return userRepository.save(changeUser(oldUser, newUser))
    }

    fun deleteUser(userId: Long): Boolean {
        if (userRepository.findById(userId).isPresent) {
            userRepository.deleteById(userId)
            return true
        }
        return false
    }

    override fun loadUserByUsername(username: String): UserModel {
        val user = userRepository.findByUsername(username)
        if (user != null)
            return user
        else throw UserNotFoundException()
    }

    fun getCurrentUser(): UserModel = SecurityContextHolder.getContext().authentication.principal as UserModel


    fun isUserExists(username: String): Boolean = userRepository.findByUsername(username) != null
}