package com.kstuca.shumovych.master.library.domain

import com.kstuca.shumovych.master.library.model.BookModel
import com.kstuca.shumovych.master.library.model.UserModel

data class Review (
        val title: String? = null,
        val description: String? = null,
        val rating: Float? = null,
        val isRecommended: Boolean? = null,
        val reviewer: UserModel? = null,
        val book: BookModel? = null
)