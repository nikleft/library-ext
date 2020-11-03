package com.kstuca.shumovych.master.library.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "roles")
data class RoleModel(

        @Id
        val id: Long,
        val name: String,
        @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
        val users: MutableSet<UserModel>? = mutableSetOf()

) : GrantedAuthority {

    override fun getAuthority(): String = name
}