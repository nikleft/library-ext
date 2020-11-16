package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.service.BookService
import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid


@Controller
class RegistrationController(val userService: UserService, val bookService: BookService) {


    @GetMapping("/registration")
    fun registration(model: Model): String {
        model.addAttribute("userForm", UserModel())
        model.addAttribute("genres", bookService.getGenresCount())
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(@ModelAttribute("userForm") @Valid userForm: UserModel, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "registration"
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует")
            return "registration"
        }
        return "redirect:/login"
    }
}