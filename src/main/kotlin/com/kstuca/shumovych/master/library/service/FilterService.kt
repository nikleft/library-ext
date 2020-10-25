package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.enums.GenreEnum
import com.kstuca.shumovych.master.library.model.BookModel
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
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
        return executeAndWrap(page, getTotalPages(builder), criteria)
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
                val predicate = builder.greaterThanOrEqualTo(root.get<Date>("year"), SimpleDateFormat("yyyy").parse(from))
                predicates.add(predicate)
            }
            return this
        }

        fun addTo(to: String?): BookCriteriaBuilder {
            if (to != null) {
                val predicate = builder.lessThanOrEqualTo(root.get<Date>("year"), SimpleDateFormat("yyyy").parse(to))
                predicates.add(predicate)
            }
            return this
        }

        fun addSort(sort: String?): BookCriteriaBuilder {
            if (sort != null) {
                when (sort) {
                    "name" -> query.orderBy(builder.desc(root.get<String>("name")))
                    "edition" -> query.orderBy(builder.desc(root.get<String>("edition")))
                    "author" -> query.orderBy(builder.desc(root.get<String>("author")))
                    "date" -> query.orderBy(builder.desc(root.get<Date>("date")))
                }
            }
            return this
        }

        fun build(): CriteriaQuery<BookModel> = query.where(*predicates.toTypedArray())
    }

    private fun executeAndWrap(currentPage: Int?, totalPages: Long, criteria: CriteriaQuery<BookModel>): CustomPage<BookModel> {
        if (currentPage != null) {
            return CustomPage(totalPages, entityManager.createQuery(criteria).setFirstResult(currentPage.toInt()).setMaxResults(DEFAULT_SIZE).resultList)
        }
        return CustomPage(totalPages, entityManager.createQuery(criteria).setMaxResults(DEFAULT_SIZE).resultList)
    }

    private fun getTotalPages(builder: CriteriaBuilder): Long {
        val countQuery: CriteriaQuery<Long> = builder.createQuery(Long::class.java)
        countQuery.select(builder.count(countQuery.from(BookModel::class.java)))
        val result = entityManager.createQuery(countQuery).singleResult / DEFAULT_SIZE
        if (entityManager.createQuery(countQuery).singleResult % DEFAULT_SIZE != 0L)
            return result + 1
        return result
    }
}