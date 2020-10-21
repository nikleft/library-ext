package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.enums.GenreEnum

data class GenreCount(
        val genre: GenreEnum,
        val count: Long
)