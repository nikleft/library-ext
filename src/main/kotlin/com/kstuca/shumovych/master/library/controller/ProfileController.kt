package com.kstuca.shumovych.master.library.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/profiles")
@Validated
class ProfileController {

    @GetMapping("/{id}")
    fun getBooks(@RequestParam(value = "id", required = false) id: Int?, model: Model): String {
        return "users/profile"
    }
}