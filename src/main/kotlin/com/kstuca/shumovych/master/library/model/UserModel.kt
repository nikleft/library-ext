package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.GenreEnum
import com.kstuca.shumovych.master.library.enums.UserStateEnum
import com.kstuca.shumovych.master.library.enums.UserTypeEnum
import org.springframework.data.annotation.CreatedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        val id: Long? = null,
        val name: String? = null,
        val surname: String? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner", fetch = FetchType.LAZY)
        val orders: Set<OrderModel>? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "reviewer", fetch = FetchType.LAZY)
        val reviews: Set<ReviewModel>? = null,

        @ManyToMany(cascade = [CascadeType.ALL])
        val friends: Set<UserModel>? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        val favouriteGenres: Set<GenreEnum>? = null,

        @Temporal(TemporalType.DATE)
        @CreatedDate
        val registrationDate: Date? = null,
        val address: String? = null,
        val phone: String? = null,
        val email: String? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        val roles: MutableSet<RoleModel>? = null,
        private val username: String? = null,
        private val password: String? = null

) : UserDetails {

    companion object {

        fun from(user: UserModel, roles: MutableSet<RoleModel>, password: String): UserModel =
                UserModel(
                        name = user.name,
                        surname = user.surname,
                        favouriteGenres = user.favouriteGenres,
                        address = user.address,
                        phone = user.phone,
                        roles = roles,
                        username = user.username,
                        password = password
                )
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = roles
    override fun getUsername(): String? = username
    override fun getPassword(): String? = password
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}