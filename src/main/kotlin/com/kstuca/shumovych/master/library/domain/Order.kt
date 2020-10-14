package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.model.UserModel
import java.util.*

data class Order(
        val id: UUID? = null,
        val owner: UserModel? = null,
        val moderator: UserModel? = null,
        val bookId: UUID? = null,
        val date: String? = null,
        val tillDate: String? = null,
        val orderId: UUID? = null,
        val orderState: String? = null,
        val path: String? = null
)