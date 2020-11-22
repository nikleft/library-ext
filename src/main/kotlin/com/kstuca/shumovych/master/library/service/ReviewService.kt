package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.model.ReviewModel
import com.kstuca.shumovych.master.library.repository.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(val reviewRepository: ReviewRepository) {

    fun saveReview(review: ReviewModel): Boolean {
        reviewRepository.save(review)
        return true
    }
}

