package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/books")
class BookController(val bookService: BookService) {

    @GetMapping
    fun getBooks(@RequestParam(value = "page", required = false) page: Int?,
                 @RequestParam(value = "size", required = false) size: Int?,
                 @RequestParam(value = "sort", required = false) sort: String?,
                 model: Model): String {
        model.addAllAttributes(bookService.getBooks(1, 1, ""))
        return "books/allBooks"
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable(value = "id") id: String, model: Model): String {
        model.addAttribute(bookService.getBook(id))
        return "books/bookDetails"
    }
}