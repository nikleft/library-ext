package com.kstuca.shumovych.master.library.domain

import java.util.*

data class Book(
        val id: UUID? = null,
        val name: String? = null,
        val author: String? = null,
        val year: String? = null,
        val edition: String? = null,
        val count: Int? = 0,
        val path: String? = null
)