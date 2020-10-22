package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController(val bookService: BookService) {

    @GetMapping("/index", "/", "/main")
    fun getMainPage(@RequestParam("name", required = false) name: String?, model: Model): String {
        val recommendations = bookService.getRecommendations(name)
        model.addAttribute("recommendations", recommendations)
        return "index"
    }

    @GetMapping("/login", "/auth")
    fun login(): String {
        return "login"
    }
}