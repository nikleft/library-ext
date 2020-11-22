package com.kstuca.shumovych.master.library.model

import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reviews")
@EntityListeners(AuditingEntityListener::class)
class ReviewModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null,

        @Temporal(TemporalType.DATE)
        @CreatedDate
        @Column(name = "review_date")
        var reviewDate: Date? = null,
        val title: String? = null,
        val description: String? = null,
        @Range(min = 0, max = 10)
        val rating: Double? = null,
        @Column(name = "is_recommended")
        val recommend: Boolean? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        val reviewer: UserModel? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        val book: BookModel? = null
): Serializable