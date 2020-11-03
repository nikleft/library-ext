package com.kstuca.shumovych.master.library.repository

import com.kstuca.shumovych.master.library.model.UserModel
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<UserModel, Long> {

    fun findByUsername(username: String): UserModel?
}