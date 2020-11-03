package com.kstuca.shumovych.master.library.model

import org.hibernate.validator.constraints.Range
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reviews")
class ReviewModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null,

        @Column(name = "review_date")
        val reviewDate: Date? = null,
        val title: String? = null,
        val description: String? = null,
        @Range(min = 0, max = 10)
        val rating: Double,
        @Column(name = "is_recommended")
        val isRecommended: Boolean? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        val reviewer: UserModel? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        val book: BookModel? = null
): Serializable