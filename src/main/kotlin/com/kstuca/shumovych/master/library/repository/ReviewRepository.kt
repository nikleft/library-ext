package com.kstuca.shumovych.master.library.repository

import com.kstuca.shumovych.master.library.model.ReviewModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<ReviewModel, Long>