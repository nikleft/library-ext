package com.kstuca.shumovych.master.library.exception

class BookNotFoundException constructor(val error: String = "book.not.found",
                                        val errorDescription: String = "Invalid book id / book not found") : Exception()
