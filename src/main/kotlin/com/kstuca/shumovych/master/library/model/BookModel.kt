package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.GenreEnum
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "books")
class BookModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null,
        val name: String? = null,
        val author: String? = null,
        @Temporal(TemporalType.DATE)
        val year: Date? = null,
        val edition: String? = null,
        @Enumerated(EnumType.STRING)
        val genre: GenreEnum? = null,
        @Lob
        val description: String? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "book", fetch = FetchType.LAZY)
        val reviews: Set<ReviewModel>? = null,
        val count: Int? = 0,
        val path: String? = null,

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val users: List<UserModel>? = null,
        @Lob
        val image: Array<Byte>? = null
): Serializable