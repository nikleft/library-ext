package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun getUsers(@RequestParam(value = "page", required = false) page: Int?,
                  @RequestParam(value = "size", required = false) size: Int?,
                  @RequestParam(value = "sort", required = false) sort: String?,
                  model: Model): String {
        model.addAllAttributes(userService.getUsers(1, 1, ""))
        return "users/users"
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") id: String, model: Model): String {
        model.addAttribute(userService.getUser(id))
        return "users/userDetails"
    }
}