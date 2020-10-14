package com.kstuca.shumovych.master.library.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/index", "/", "/main")
    fun getMainPage(): String {
        return "index"
    }

    @GetMapping("/login", "/auth")
    fun login(): String {
        return "login"
    }
}