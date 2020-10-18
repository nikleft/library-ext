package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.exception.UserNotFoundException
import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {

    fun getUsers(page: Int?, size: Int?, sort: String?): List<UserModel> {
        return when {
            page != null && size != null && sort != null -> userRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).content
            page != null && size != null -> userRepository.findAll(PageRequest.of(page, size)).content
            else -> userRepository.findAll(PageRequest.of(1, 10)).content
        }
    }

    fun getUser(id: Int): UserModel {
        val user: Optional<UserModel> = userRepository.findById(id)
        if (user.isPresent) return user.get() else throw UserNotFoundException()
    }
}