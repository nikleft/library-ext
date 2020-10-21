package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.domain.GenreCount
import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

private const val DEFAULT_SIZE = 2
private const val DEFAULT_PAGE = 0

@Service
class BookService(val bookRepository: BookRepository) {

    fun getBooks(page: Int?, sort: String?): Page<BookModel> {
        return when {
            page != null && sort != null -> bookRepository.findAll(PageRequest.of(page, DEFAULT_SIZE, Sort.by(sort)))
            page != null -> bookRepository.findAll(PageRequest.of(page, DEFAULT_SIZE))
            else -> bookRepository.findAll(PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE))
        }
    }

    fun getBook(id: Int): BookModel {
        val book: Optional<BookModel> = bookRepository.findById(id)
        if (book.isPresent) return book.get() else throw BookNotFoundException()
    }

    fun getGenresCount(): List<GenreCount> = bookRepository.findAllGenresWithCount()
}