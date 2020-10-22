package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.enums.GenreEnum

data class Book(
        val id: Int? = null,
        val name: String? = null,
        val author: String? = null,
        val year: String? = null,
        val edition: String? = null,
        val genre: GenreEnum? = null,
        val description: String? = null,
        val count: Int? = 0,
        val path: String? = null,
        val image: Array<Byte>? = null
)