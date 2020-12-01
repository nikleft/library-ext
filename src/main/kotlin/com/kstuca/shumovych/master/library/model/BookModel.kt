package com.kstuca.shumovych.master.library.model

import com.kstuca.shumovych.master.library.enums.GenreEnum
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "books")
class BookModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null,
        var name: String? = null,
        var author: String? = null,
        @DateTimeFormat(pattern = "dd.MM.yyyy")
        var year: LocalDate? = null,
        var edition: String? = null,
        @Enumerated(EnumType.STRING)
        var genre: GenreEnum? = null,
        @Lob
        var description: String? = null,
        @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], mappedBy = "book", fetch = FetchType.LAZY)
        val reviews: Set<ReviewModel>? = null,
        val path: String? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], fetch = FetchType.EAGER)
        val users: MutableList<UserModel>? = null,
        @Lob
        val image: Array<Byte>? = null
): Serializable