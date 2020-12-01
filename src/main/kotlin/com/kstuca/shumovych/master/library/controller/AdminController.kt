package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.model.RoleModel
import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.service.BookService
import com.kstuca.shumovych.master.library.service.RoleService
import com.kstuca.shumovych.master.library.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


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
        model.addAttribute("users", roleService.getRoleById(3).get().users)
        return "admin/editors"
    }


    @GetMapping("/users/update/{id}")
    fun getUserForUpdate(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("user", userService.findUserById(id))
        model.addAttribute("genres", bookService.getGenresCount())
        return "admin/user"
    }

    @PostMapping("/users/update/{id}")
    fun updateUser(@PathVariable("id") id: Long,
                   @ModelAttribute("user") @Valid user: UserModel,
                   model: Model): String {
        userService.updateUser(userService.findUserById(id), user)
        return "redirect:/admin/users"
    }

//    @PostMapping("/users/delete/{id}")
//    fun deleteUser(@PathVariable("id") id: Long, model: Model): String {
//        userService.deleteUser(id)
//        model.addAttribute("users", roleService.getRoleById(1).get().users)
//        return "redirect:/admin/users"
//    }


    @GetMapping("/editors/update/{id}")
    fun getEditorForUpdate(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("user", userService.findUserById(id))
        return "admin/editor"
    }

    @PostMapping("/editors/update/{id}")
    fun updateEditor(@PathVariable("id") id: Long,
                     @ModelAttribute("user") @Valid editor: UserModel,
                     model: Model): String {
        userService.changeUser(userService.findUserById(id), editor)
        return "redirect:/admin/editors"
    }

    @GetMapping("/editors/create")
    fun getAddEditorPage(model: Model): String {
        model.addAttribute("user", UserModel())
        return "admin/newEditor"
    }

    @PostMapping("/editors/create")
    fun createEditor(@ModelAttribute("user") @Valid editor: UserModel): String {
        userService.registerUser(editor, roleService.getRoleById(3).get())
        return "redirect:/admin/editors"
    }

    @PostMapping("/editors/delete/{id}")
    fun deleteEditor(@PathVariable("id") id: Long, model: Model): String {
        userService.deleteUser(id)
        model.addAttribute("users", roleService.getRoleById(1).get().users)
        return "redirect:/admin/editors"
    }


    @GetMapping("/books")
    fun getAllBooks(model: Model): String {
        model.addAttribute("books", bookService.getAllBooks())
        return "admin/books"
    }

    @GetMapping("/books/update/{id}")
    fun getBookForUpdate(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("book", bookService.getBook(id))
        return "admin/book"
    }

    @PostMapping("/books/update/{id}")
    fun updateBook(@PathVariable("id") id: Long,
                   @ModelAttribute("changeBookForm") @Valid changeBookForm: BookModel,
                   model: Model): String {
        bookService.changeBook(id, changeBookForm)
        model.addAttribute("books", bookService.getAllBooks())
        return "redirect:/admin/books"
    }

    @GetMapping("/books/create")
    fun getCreateBookPage(model: Model): String {
        model.addAttribute("book", BookModel())
        return "/admin/newBook"
    }

    @PostMapping("/books/create")
    fun createBook(@ModelAttribute("changeBookForm") @Valid changeBookForm: BookModel,
                          model: Model): String {
        bookService.saveBook(changeBookForm)
        model.addAttribute("books", bookService.getAllBooks())
        return "redirect:/admin/books"
    }

    @PostMapping("/books/delete/{id}")
    fun deleteBook(@PathVariable("id") id: Long, model: Model): String {
        bookService.deleteBook(id)
        model.addAttribute("books", bookService.getAllBooks())
        return "redirect:/admin/books"
    }
}