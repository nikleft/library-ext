package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.model.ReviewModel
import com.kstuca.shumovych.master.library.service.BookService
import com.kstuca.shumovych.master.library.service.ReviewService
import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
@RequestMapping("/reviews")
class ReviewController(val reviewService: ReviewService,
                       val userService: UserService,
                       val bookService: BookService) {


    @PostMapping
    fun addNewReview(@ModelAttribute("reviewForm") @Valid reviewForm: ReviewModel,
                     @RequestParam book: Long,
                     @RequestParam reviewer: Long): String {
        val newReview = ReviewModel(title = reviewForm.title,
                description = reviewForm.description,
                rating = reviewForm.rating,
                book = bookService.getBook(book),
                reviewer = userService.findUserById(reviewer))

        reviewService.saveReview(newReview)

        return "redirect:books/$book"
    }
}