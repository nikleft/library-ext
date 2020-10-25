package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.constraints.Min

@Controller
@RequestMapping("/books")
@Validated
class BookController(val bookService: BookService) {

    @GetMapping
    fun getBooks(@Min(1) @RequestParam(value = "page", required = false) page: Int?,
                 @RequestParam(value = "sort", required = false) sort: String?,
                 @RequestParam(value = "genres", required = false) genres: List<String>?,
                 @RequestParam(value = "from", required = false) from: String?,
                 @RequestParam(value = "to", required = false) to: String?,
                 model: Model): String {
        bookService.getBooks(page, sort, genres, from, to, model)
        return "books/allBooks"
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable(value = "id") id: Int, model: Model): String {
        var book = bookService.getBook(id)
        model.addAttribute("book", book)
        model.addAttribute("overallRating", bookService.getOverallRating(book))
        return "books/bookDetails"
    }
}