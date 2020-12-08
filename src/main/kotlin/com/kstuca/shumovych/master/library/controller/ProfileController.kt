package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.service.BookService
import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.validation.Valid

@Controller
@RequestMapping("/profiles")
@Validated
class ProfileController(val userService: UserService,
                        val bookService: BookService) {

    @GetMapping("/{id}")
    fun getProfile(@PathVariable("id") id: Long, model: Model): String {
        val user = userService.findUserById(id)
        model.addAttribute("user", user)
        model.addAttribute("changeProfileForm", userService.getCurrentUser())
        model.addAttribute("genres", bookService.getGenresCount())
        return "users/profile"
    }

    @PostMapping("/{id}")
    fun changeProfile(@PathVariable("id") id: Long,
                      @ModelAttribute("changeProfileForm") @Valid changeProfileForm: UserModel,
                      model: Model): String {
        val authenticated = (SecurityContextHolder.getContext().authentication.principal as UserModel)

        userService.updateCurrentUser(userService.changeUser(authenticated, changeProfileForm))

        val user = userService.findUserById(id)
        model.addAttribute("user", user)
        model.addAttribute("changeProfileForm", userService.getCurrentUser())
        model.addAttribute("genres", bookService.getGenresCount())
        return "redirect:/profiles/$id"
    }


    @GetMapping("/addFriend/{id}")
    fun addFriendById(@PathVariable(value = "id") id: Long, model: Model): String {
        val user = userService.findUserById(userService.getCurrentUser().id!!)
        val friend = userService.findUserById(id)
        user.friends?.add(friend)
        friend.friends?.add(user)

        userService.updateCurrentUser(user)
        model.addAttribute("user", friend)
        return "redirect:/profiles/$id"
    }

    @GetMapping("/removeFriend/{id}")
    fun removeFriendById(@PathVariable(value = "id") id: Long, model: Model): String {
        val user = userService.findUserById(userService.getCurrentUser().id!!)
        val friend = userService.findUserById(id)
        user.friends?.remove(friend)
        friend.friends?.remove(user)

        userService.updateCurrentUser(user)
        model.addAttribute("user", friend)
        return "redirect:/profiles/$id"
    }

    @PostMapping("/image/{id}")
    fun addUserImage(@RequestParam("file") image: MultipartFile, @PathVariable("id") userId: Long): String? {
        val file = File(File("./").absolutePath + "/build/resources/main/static/images/users/")
        val filename = UUID.randomUUID().toString() + ".png"
        val saveTo: Path = Paths.get(file.absolutePath + "/" + filename)
        try {
            Files.copy(image.inputStream, saveTo)

            val authenticated = SecurityContextHolder.getContext().authentication.principal as UserModel

            authenticated.image = filename
            userService.updateCurrentUser(authenticated)
        } catch (e: Exception) {
            e.printStackTrace()
            "Error : " + e.message
        }
        return "redirect:/profiles/$userId"
    }
}