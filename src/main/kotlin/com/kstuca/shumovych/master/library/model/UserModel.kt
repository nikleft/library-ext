package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.UserStateEnum
import com.kstuca.shumovych.master.library.enums.UserTypeEnum
import javax.persistence.*

@Entity
@Table(name = "users")
class UserModel(

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
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner", fetch = FetchType.LAZY)
        val orders: Set<OrderModel>? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "reviewer", fetch = FetchType.LAZY)
        val reviews: Set<ReviewModel>? = null,
        @Enumerated(EnumType.STRING)
        val state: UserStateEnum? = null
)