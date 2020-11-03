package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class AdminController(val userService: UserService) {


    @GetMapping("/admin")
    fun userList(model: Model): String {
        model.addAttribute("allUsers", userService.allUsers())
        return "admin"
    }

    @PostMapping("/admin")
    fun deleteUser(@RequestParam(required = true, defaultValue = "") userId: Long,
                   @RequestParam(required = true, defaultValue = "") action: String,
                   model: Model): String {
        if (action == "delete") {
            userService.deleteUser(userId)
        }
        return "redirect:/admin"
    }

    @GetMapping("/admin/users/{userId}")
    fun gtUser(@PathVariable("userId") userId: Long, model: Model): String {
        model.addAttribute("allUsers", userService.findUserById(userId))
        return "admin"
    }
}