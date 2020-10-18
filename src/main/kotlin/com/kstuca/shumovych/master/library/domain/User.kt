package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.enums.UserStateEnum
import com.kstuca.shumovych.master.library.enums.UserTypeEnum
import com.kstuca.shumovych.master.library.model.OrderModel
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class User(
        val id: Int? = null,
        val name: String? = null,
        val surname: String? = null,
        val password: String? = null,
        val login: String? = null,
        @Enumerated(EnumType.STRING)
        val type: UserTypeEnum? = null,
        val orders: Set<OrderModel>? = null,
        @Enumerated(EnumType.STRING)
        val state: UserStateEnum? = null
)