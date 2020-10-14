package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.enums.UserStateEnum
import com.kstuca.shumovych.master.library.enums.UserTypeEnum
import com.kstuca.shumovych.master.library.model.OrderModel
import java.util.*

data class User(
        val id: UUID? = null,
        val name: String? = null,
        val surname: String? = null,
        val password: String? = null,
        val login: String? = null,
        val type: UserTypeEnum? = null,
        val orders: Set<OrderModel>? = null,
        val state: UserStateEnum? = null
)