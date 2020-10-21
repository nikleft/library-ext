package com.kstuca.shumovych.master.library.model

import javax.persistence.*

@Entity
@Table(name = "reviews")
data class ReviewModel (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        val id: Int? = null,
        val title: String? = null,
        val description: String? = null,
        val rating: Float? = null,
        val isRecommended: Boolean? = null,
        @ManyToOne
        val reviewer: UserModel? = null,
        @ManyToOne
        val book: BookModel? = null
)