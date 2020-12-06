package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(val bookService: BookService) {

    @GetMapping("/index", "/", "/main")
    fun getMainPage(model: Model): String {
        val recommendations = bookService.getCurrentUserRecommendations()
        model.addAttribute("recommendations", recommendations)
        return "index"
    }

}