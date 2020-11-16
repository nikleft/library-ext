package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.websocket.server.PathParam

@Controller
@RequestMapping("/profiles")
@Validated
class ProfileController(val userService: UserService) {

    @GetMapping("/{id}")
    fun getBooks(@PathVariable("id") id: Long, model: Model): String {
        val user = userService.findUserById(id)
        model.addAttribute("user", user)
        return "users/profile"
    }

    @GetMapping("/addFriend/{id}")
    fun addFriendById(@PathVariable(value = "id") id: Long, model: Model): String {
        val user = userService.findUserById(userService.getCurrentUser().id!!)
        val friend = userService.findUserById(id)
        user.friends?.add(friend)
        userService.updateUser(user)
        model.addAttribute("user", friend)
        return "users/profile"
    }

    @GetMapping("/removeFriend/{id}")
    fun removeFriendById(@PathVariable(value = "id") id: Long, model: Model): String {
        val user = userService.findUserById(userService.getCurrentUser().id!!)
        val friend = userService.findUserById(id)
        user.friends?.remove(friend)
        userService.updateUser(user)
        model.addAttribute("user", friend)
        return "users/profile"
    }
}