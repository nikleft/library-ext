package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.repository.BookRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(val bookRepository: BookRepository) {

    fun getBooks(page: Int?, size: Int?, sort: String?): List<BookModel> {
        return when {
            page != null && size != null && sort != null -> bookRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).content
            page != null && size != null -> bookRepository.findAll(PageRequest.of(page, size)).content
            else -> bookRepository.findAll().toList()
        }
    }

    fun getBook(id: Int): BookModel {
        val book: Optional<BookModel> = bookRepository.findById(id)
        if (book.isPresent) return book.get() else throw BookNotFoundException()
    }
}