package com.kstuca.shumovych.master.library.domain.forms

data class FilterForm (
        val genres: List<String>,
        val from: Int?,
        val to: Int?,
        val sort: String?
)