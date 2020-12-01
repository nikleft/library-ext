package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.domain.GenreCount
import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.repository.BookRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*

private const val DEFAULT_SIZE = 2
private const val DEFAULT_PAGE = 0

@Service
class BookService(val bookRepository: BookRepository, val filterService: FilterService) {

    fun getAllBooks(): MutableIterable<BookModel> = bookRepository.findAll()

    fun getBooks(page: Int?, sort: String?, genres: List<String>?, from: String?, to: String?, model: Model?) {
        val result = filterService.applyBookFilters(page, sort, genres, from, to)
        fulfillModel(result, page, sort, genres, from, to, model!!)
    }

    fun getBook(id: Long): BookModel {
        val book: Optional<BookModel> = bookRepository.findById(id)
        if (book.isPresent) return book.get() else throw BookNotFoundException()
    }

    fun fulfillModel(result: CustomPage<BookModel>, page: Int?, sort: String?, genres: List<String>?, from: String?, to: String?, model: Model) {
        model.addAttribute("books", result.itemsList)
        model.addAttribute("totalPages", result.totalPages)
        model.addAttribute("activePage", page)
        model.addAttribute("genres", getGenresCount())

        model.addAttribute("sortActive", sort)
        model.addAttribute("genresActive", genres)
        model.addAttribute("fromActive", from)
        model.addAttribute("toActive", to)
    }

    fun updateBook(book: BookModel) = bookRepository.save(book)

    fun changeBook(id: Long, book: BookModel) {
        val old = bookRepository.findById(id).get()
        book.name.let { old.name = book.name}
        book.author.let { old.author = book.author}
        book.edition.let { old.edition = book.edition}
        book.year.let { old.year = book.year}
        book.description.let { old.description = book.description}
        book.genre.let { old.genre = book.genre}
        bookRepository.save(old)
    }

    fun deleteBook(id: Long) = bookRepository.deleteById(id)

    fun saveBook(book: BookModel) = bookRepository.save(book)

    fun getGenresCount(): List<GenreCount> = bookRepository.findAllGenresWithCount()

    fun getOverallRating(book: BookModel): Double =
            book.reviews?.sumByDouble { it.rating!! }!!.div(book.reviews.size)

    fun getRecommendations(name: String?): List<BookModel> {
        return bookRepository.findAll(PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE)).content
    }
}