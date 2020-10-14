package com.kstuca.shumovych.master.library.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class OrderModel(
        @Id
        val id: UUID? = null,
        @ManyToOne
        val owner: UserModel? = null,
        @ManyToOne
        val moderator: UserModel? = null,
        val bookId: UUID? = null,
        val date: String? = null,
        val tillDate: String? = null,
        val orderId: UUID? = null,
        val orderState: String? = null,
        val path: String? = null
)