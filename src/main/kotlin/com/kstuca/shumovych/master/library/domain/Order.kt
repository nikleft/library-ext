package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.model.UserModel

data class Order(
        val id: Int? = null,
        val owner: UserModel? = null,
        val moderator: UserModel? = null,
        val bookId: Int? = null,
        val date: String? = null,
        val tillDate: String? = null,
        val orderId: Int? = null,
        val orderState: String? = null,
        val path: String? = null
)