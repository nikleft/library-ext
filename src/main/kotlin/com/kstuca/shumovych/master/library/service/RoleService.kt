package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository) {


    fun getRoleById(id: Long) = roleRepository.findById(id)
}