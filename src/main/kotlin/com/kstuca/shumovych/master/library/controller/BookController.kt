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
                 @RequestParam(value = "sort", required = false) sort: String?,
                 model: Model): String {
        val result = bookService.getBooks(page, sort)
        model.addAttribute("books", result.content)
        model.addAttribute("totalPages", result.totalPages)
        model.addAttribute("activePage", page)
        model.addAttribute("genres", bookService.getGenresCount())
        return "books/allBooks"
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable(value = "id") id: Int, model: Model): String {
        model.addAttribute("book", bookService.getBook(id))
        return "books/bookDetails"
    }
}