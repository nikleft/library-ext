package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.BookService
import com.kstuca.shumovych.master.library.service.RoleService
import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/admin")
class AdminController(val userService: UserService,
                      val roleService: RoleService,
                      val bookService: BookService) {


    @GetMapping("/users")
    fun getAllUsers(model: Model): String {
        model.addAttribute("users", roleService.getRoleById(1).get().users)
        return "admin/users"
    }

    @GetMapping("/editors")
    fun getAllEditors(model: Model): String {
        model.addAttribute("editors", roleService.getRoleById(2).get().users)
        return "admin/editors"
    }

    @GetMapping("/editors/update/{id}", "/users/update/{id}")
    fun getUserForUpdate(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("user", userService.findUserById(id))
        return "admin/user"
    }

    //hmem
    @PostMapping("/editors/update/{id}", "/users/update/{id}")
    fun updateUser(@PathVariable("id") id: Long, model: Model): String {


        return "redirect:admin/users/$id"
    }

    //hmem
    @PostMapping("/editors/delete/{id}", "/users/delete/{id}")
    fun deleteUser(@PathVariable("id") id: Long, model: Model): String {
        userService.deleteUser(id)
        model.addAttribute("users", roleService.getRoleById(1).get().users)
        return "redirect:admin/users"
    }

    @GetMapping("/books")
    fun getAllBooks(model: Model): String {
        model.addAttribute("books", bookService.getAllBooks())
        return "admin/books"
    }

    @GetMapping("/books/update/{id}")
    fun getBookForUpdate(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("book", bookService.getBook(id))
        return "admin/books/$id"
    }

    @PostMapping("/books/update/{id}")
    fun updateBook(@PathVariable("id") id: Long, model: Model): String {


        model.addAttribute("books", bookService.getAllBooks())
        return "redirect:admin/books"
    }

    @PostMapping("/books/delete/{id}")
    fun deleteBook(@PathVariable("id") id: Long, model: Model): String {
        bookService.deleteBook(id)
        model.addAttribute("books", bookService.getAllBooks())
        return "redirect:admin/books"
    }
}