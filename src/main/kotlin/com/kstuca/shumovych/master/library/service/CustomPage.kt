package com.kstuca.shumovych.master.library.service

data class CustomPage<T> (
        val totalPages: Long,
        val itemsList: List<T>
)
