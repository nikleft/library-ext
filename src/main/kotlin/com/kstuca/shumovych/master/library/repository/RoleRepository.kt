package com.kstuca.shumovych.master.library.repository

import com.kstuca.shumovych.master.library.model.RoleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<RoleModel, Long>