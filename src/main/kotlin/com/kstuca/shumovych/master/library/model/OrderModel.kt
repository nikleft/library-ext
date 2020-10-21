package com.kstuca.shumovych.master.library.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class OrderModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        val id: Int? = null,
        @ManyToOne
        val owner: UserModel? = null,
        @ManyToOne
        val moderator: UserModel? = null,
        val bookId: Int? = null,
        val date: String? = null,
        val tillDate: String? = null,
        val orderId: Int? = null,
        val orderState: String? = null
)