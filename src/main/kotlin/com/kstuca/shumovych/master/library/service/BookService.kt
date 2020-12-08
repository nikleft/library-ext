package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.domain.GenreCount
import com.kstuca.shumovych.master.library.enums.RecommendationEnum
import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.model.UserModel
import com.kstuca.shumovych.master.library.repository.BookRepository
import info.debatty.java.stringsimilarity.JaroWinkler
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*

private const val DEFAULT_SIZE = 2
private const val DEFAULT_PAGE = 0

@Service
class BookService(val bookRepository: BookRepository,
                  val filterService: FilterService) {

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
        book.name.let { old.name = book.name }
        book.author.let { old.author = book.author }
        book.edition.let { old.edition = book.edition }
        book.year.let { old.year = book.year }
        book.description.let { old.description = book.description }
        book.genre.let { old.genre = book.genre }
        bookRepository.save(old)
    }

    fun deleteBook(id: Long) = bookRepository.deleteById(id)

    fun saveBook(book: BookModel) = bookRepository.save(book)

    fun getGenresCount(): List<GenreCount> = bookRepository.findAllGenresWithCount()

    fun getOverallRating(book: BookModel): Double =
            book.reviews?.sumByDouble { it.rating!! }!!.div(book.reviews.size)

    val jw: JaroWinkler = JaroWinkler()

    fun getCurrentUserRecommendations(): List<BookModel> {
        val currentUser = (SecurityContextHolder.getContext().authentication.principal as UserModel)
        val books = currentUser.books!!
        return when (currentUser.recommendationType) {
            RecommendationEnum.AUTHOR -> getAuthorUserRecommendations(books, getAllBooks())
            RecommendationEnum.REVIEWS -> getReviewsUserRecommendations(books, getAllBooks())
            RecommendationEnum.GENRE -> getGenresUserRecommendations(books, getAllBooks())
            else -> getDefaultUserRecommendations(books, getAllBooks())
        }
    }

    private fun getAuthorUserRecommendations(userBooks: MutableList<BookModel>, allBooks: MutableIterable<BookModel>): List<BookModel> {
        val similarities: MutableList<MutableMap<Double, BookModel>> = mutableListOf()

        userBooks.forEachIndexed { index, it ->
            similarities.add(mutableMapOf())
            allBooks.forEach { book ->
                if (book != it) {
                    val total: Double = if (book.reviews != null && book.reviews.isNotEmpty())
                        book.reviews.sumByDouble { it.rating!! }
                    else 5.0
                    val key = jw.similarity(it.author, book.author) + total
                    similarities[index][key] = book
                }
            }
        }
        return sort(similarities, userBooks)
    }

    private fun getReviewsUserRecommendations(userBooks: MutableList<BookModel>, allBooks: MutableIterable<BookModel>): List<BookModel> {
        val similarities: MutableList<MutableMap<Double, BookModel>> = mutableListOf()

        userBooks.forEachIndexed { index, it ->
            similarities.add(mutableMapOf())
            allBooks.forEach { book ->
                if (book != it) {
                    val total: Double = if (book.reviews != null && book.reviews.isNotEmpty())
                        book.reviews.sumByDouble { it.rating!! }
                    else 5.0
                    similarities[index][total / 10] = book
                }
            }
        }
        return sort(similarities, userBooks)
    }

    private fun getGenresUserRecommendations(userBooks: MutableList<BookModel>, allBooks: MutableIterable<BookModel>): List<BookModel> {
        val similarities: MutableList<MutableMap<Double, BookModel>> = mutableListOf()

        userBooks.forEachIndexed { index, it ->
            similarities.add(mutableMapOf())
            allBooks.forEach { book ->
                if (book != it) {
                    val total: Double = if (book.reviews != null && book.reviews.isNotEmpty())
                        book.reviews.sumByDouble { it.rating!! }
                    else 5.0
                    val key = jw.similarity(it.genre?.type, book.genre?.type) + total / 10
                    similarities[index][key] = book
                }
            }
        }
        return sort(similarities, userBooks)
    }

    private fun getDefaultUserRecommendations(userBooks: MutableList<BookModel>, allBooks: MutableIterable<BookModel>): List<BookModel> {
        val similarities: MutableList<MutableMap<Double, BookModel>> = mutableListOf()

        userBooks.forEachIndexed { index, it ->
            similarities.add(mutableMapOf())
            allBooks.forEach { book ->
                if (book != it) {
                    val total: Double = if (book.reviews != null && book.reviews.isNotEmpty())
                        book.reviews.sumByDouble { it.rating!! }
                    else 5.0
                    val key = jw.similarity(it.author, book.author) + jw.similarity(it.genre?.type, book.genre?.type) + total / 10
                    similarities[index][key] = book
                }
            }
        }
        return sort(similarities, userBooks)
    }

    private fun sort(similarities: MutableList<MutableMap<Double, BookModel>>, books: List<BookModel>): List<BookModel> {
        val result = mutableListOf<BookModel>()
        similarities.forEach {
            addNewBookToResult(result, it, it[it.keys.max()]!!, books)
        }
        return result
    }

    private fun addNewBookToResult(result: MutableList<BookModel>, bookToAdd: MutableMap<Double, BookModel>, bookModel: BookModel, books: List<BookModel>) {
        if (books.contains(bookModel) || result.contains(bookModel)) {
            bookToAdd.remove(bookToAdd.keys.max())
            val maxValueBook = bookToAdd[bookToAdd.keys.max()]
            if (maxValueBook != null) {
                addNewBookToResult(result, bookToAdd, maxValueBook, books)
            }
        } else result.add(bookModel)
    }
}