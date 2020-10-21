package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.UserStateEnum
import com.kstuca.shumovych.master.library.enums.UserTypeEnum
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        val id: Int? = null,
        val name: String? = null,
        val surname: String? = null,
        val password: String? = null,
        val login: String? = null,
        @Enumerated(EnumType.STRING)
        val type: UserTypeEnum? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner")
        val orders: Set<OrderModel>? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "reviewer")
        val reviews: Set<ReviewModel>? = null,
        @Enumerated(EnumType.STRING)
        val state: UserStateEnum? = null
)