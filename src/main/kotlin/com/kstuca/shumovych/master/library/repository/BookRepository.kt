package com.kstuca.shumovych.master.library.repository

import com.kstuca.shumovych.master.library.domain.GenreCount
import com.kstuca.shumovych.master.library.model.BookModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : PagingAndSortingRepository<BookModel, Int>{

    @Query(value = "SELECT new com.kstuca.shumovych.master.library.domain.GenreCount(genre, COUNT(*) as count) FROM BookModel GROUP BY genre", nativeQuery = false)
    fun findAllGenresWithCount(): List<GenreCount>
}