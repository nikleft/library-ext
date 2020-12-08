package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.enums.GenreEnum
import com.kstuca.shumovych.master.library.model.BookModel
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


private const val DEFAULT_SIZE = 2

@Service
class FilterService(@PersistenceContext val entityManager: EntityManager) {

    fun applyBookFilters(page: Int?, sort: String?, genres: List<String>?, from: String?, to: String?): CustomPage<BookModel> {
        val builder = entityManager.criteriaBuilder
        val emptyQuery = builder.createQuery(BookModel::class.java)
        val root = emptyQuery.from(BookModel::class.java)
        val criteria = BookCriteriaBuilder(builder, emptyQuery, root)
                .addSort(sort)
                .addGenres(genres)
                .addFrom(from)
                .addTo(to)
                .build()
        return executeAndWrap(page, criteria)
    }

    class BookCriteriaBuilder(val builder: CriteriaBuilder,
                              val query: CriteriaQuery<BookModel>,
                              val root: Root<BookModel>) {

        val predicates: MutableList<Predicate> = mutableListOf()

        fun addGenres(genres: List<String>?): BookCriteriaBuilder {
            if (genres != null && genres.isNotEmpty()) {
                val genreType = root.get<GenreEnum>("genre")
                predicates.add(genreType.`in`(genres.map { GenreEnum.valueOf(it.toUpperCase()) }))
            }
            return this
        }

        fun addFrom(from: String?): BookCriteriaBuilder {
            if (from != null) {
                val predicate = builder.greaterThanOrEqualTo(root.get<LocalDate>("year"), LocalDate.from(SimpleDateFormat("yyyy").parse(from).toInstant().atZone(ZoneId.systemDefault())))
                predicates.add(predicate)
            }
            return this
        }

        fun addTo(to: String?): BookCriteriaBuilder {
            if (to != null) {
                val predicate = builder.lessThanOrEqualTo(root.get<LocalDate>("year"), LocalDate.from(SimpleDateFormat("yyyy").parse(to).toInstant().atZone(ZoneId.systemDefault())))
                predicates.add(predicate)
            }
            return this
        }

        fun addSort(sort: String?): BookCriteriaBuilder {
            if (sort != null) {
                when (sort) {
                    "name" -> query.orderBy(builder.asc(root.get<String>("name")))
                    "edition" -> query.orderBy(builder.asc(root.get<String>("edition")))
                    "author" -> query.orderBy(builder.asc(root.get<String>("author")))
                    "date" -> query.orderBy(builder.asc(root.get<LocalDate>("date")))
                }
            }
            return this
        }

        fun build(): CriteriaQuery<BookModel> = query.where(*predicates.toTypedArray())
    }

    private fun executeAndWrap(currentPage: Int?, criteria: CriteriaQuery<BookModel>): CustomPage<BookModel> {
        if (currentPage != null) {
            val result = entityManager.createQuery(criteria).setFirstResult((currentPage.toInt() - 1) * 2).setMaxResults(DEFAULT_SIZE).resultList
            return CustomPage(getTotalPages(entityManager.createQuery(criteria).setFirstResult(0).resultList.size), result)
        }
        val result = entityManager.createQuery(criteria).setFirstResult(0).setMaxResults(DEFAULT_SIZE).resultList
        return CustomPage(getTotalPages(entityManager.createQuery(criteria).setFirstResult(0).resultList.size), result)
    }

    private fun getTotalPages(totalPages: Int): Long {
        val result = totalPages.toLong() / DEFAULT_SIZE
        if (totalPages.toLong() % DEFAULT_SIZE != 0L)
            return result + 1
        return result
    }
}