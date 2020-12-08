package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.GenreEnum
import com.kstuca.shumovych.master.library.enums.RecommendationEnum
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
class UserModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Long? = null,
        var name: String? = null,
        var surname: String? = null,
        @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], mappedBy = "reviewer", fetch = FetchType.EAGER)
        val reviews: Set<ReviewModel>? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], fetch = FetchType.EAGER)
        val friends: MutableSet<UserModel>? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        val favouriteGenres: Set<GenreEnum>? = null,

        @Temporal(TemporalType.DATE)
        @CreatedDate
        var registrationDate: Date? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], fetch = FetchType.EAGER, mappedBy = "users")
        val books: MutableList<BookModel>? = null,
        var address: String? = null,
        var phone: String? = null,
        var email: String? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], fetch = FetchType.EAGER)
        val roles: MutableSet<RoleModel>? = null,
        private val username: String? = null,
        private val password: String? = null,
        var image: String? = null,

        @Enumerated(EnumType.STRING)
        var recommendationType: RecommendationEnum? = RecommendationEnum.DEFAULT


) : UserDetails, Serializable {

    companion object {

        fun from(user: UserModel, roles: MutableSet<RoleModel>, password: String): UserModel =
                UserModel(
                        name = user.name,
                        surname = user.surname,
                        favouriteGenres = user.favouriteGenres,
                        address = user.address,
                        phone = user.phone,
                        email = user.email,
                        books = user.books,
                        roles = roles,
                        username = user.username,
                        recommendationType = user.recommendationType,
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