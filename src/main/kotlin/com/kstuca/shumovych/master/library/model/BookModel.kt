package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.GenreEnum
import javax.persistence.*

@Entity
@Table(name = "books")
data class BookModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        val id: Int? = null,
        val name: String? = null,
        val author: String? = null,
        val year: String? = null,
        val edition: String? = null,
        @Enumerated(EnumType.STRING)
        val genre: GenreEnum? = null,
        @Lob
        val description: String? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "book")
        val reviews: Set<ReviewModel>? = null,
        val count: Int? = 0,
        val path: String? = null,
        @Lob
        val image: Array<Byte>? = null
)